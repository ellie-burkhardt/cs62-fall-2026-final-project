
# Imports
import os 
import calendar
import requests
import cdsapi
import numpy as np
import pandas as pd
import xarray as xr

from pathlib import Path
from dotenv import load_dotenv

# ============= Configuration of project roots and API data ==============
PROJECT_ROOT = Path(__file__).resolve().parent.parent
load_dotenv(PROJECT_ROOT / ".env")

NOAA_TOKEN = os.getenv("NOAA_API_TOKEN")

if not NOAA_TOKEN:
    raise ValueError("NOAA_API_TOKEN not found in .env")

NOAA_URL = "https://www.ncei.noaa.gov/cdo-web/api/v2/data"
NOAA_STATION = "GHCND:USW00003102"

# Claremont, California
LATITUDE = 34.0967
LONGITUDE = -117.7198

# Dates of Data
START_DATE = "2023-11-22"
END_DATE = "2026-11-20"

TIMEZONE = "America/Los_Angeles"

# Temporary folders/files
NOAA_DIR = PROJECT_ROOT / "data/raw/noaa"
ERA5_DIR = PROJECT_ROOT / "data/raw/era5"
# Make the directories if they do not exist
NOAA_DIR.mkdir(parents=True, exist_ok=True)
ERA5_DIR.mkdir(parents=True, exist_ok=True)

# Final file Names
NOAA_OUTPUT = PROJECT_ROOT / "noaa_weather_data.txt"
ERA5_OUTPUT = PROJECT_ROOT / "era5_humidity_data.txt"
OUTPUT_FILE = PROJECT_ROOT / "claremont_weather_data.txt"

# Variables for NOAA
NOAA_VARIABLES = ["TMAX", "TMIN", "PRCP", "AWND"]

# Variables for ERA5
ERA5_VARIABLES = [
    "2m_temperature",
    "2m_dewpoint_temperature"
]

# cds that bih
cds = cdsapi.Client()

# ========================== Download NOAA Data ==========================
def get_noaa_data(start, end):

    # Itty bitty little cache
    cache = NOAA_DIR / f"noaa_{start}_{end}.txt"

    # Make sure it exists
    if cache.exists():
        return pd.read_csv(cache, sep="\t")

    print(f"Downloading NOAA: {start} to {end}")

    # Set the parameters
    params = [
        ("datasetid", "GHCND"),
        ("stationid", NOAA_STATION),
        ("startdate", start),
        ("enddate", end),
        ("units", "standard"),
        ("limit", 1000)
    ]

    # Append the parameters to the datatypeid
    for variable in NOAA_VARIABLES:
        params.append(("datatypeid", variable))

    records = []
    offset = 1

    # Loop through the necessary records
    while True:

        # retrieve records
        response = requests.get(
            NOAA_URL,
            headers={"token": NOAA_TOKEN.strip()},
            params=params + [("offset", offset)],
            timeout=60
        )

        response.raise_for_status()

        results = response.json().get("results", [])

        if not results:
            break

        records.extend(results)

        if len(results) < 1000:
            break

        offset += len(results)

    df = pd.DataFrame(records)

    df.to_csv(cache, sep="\t", index=False)

    return df

# =========================== Process NOAA Data ===========================
def process_noaa():

    # New array of date ranges
    date_ranges = []

    # Configure dates
    current = pd.Timestamp(START_DATE)
    end = pd.Timestamp(END_DATE)

    # Loop through dates while extracting information
    while current <= end:

        chunk_end = min(
            current + pd.DateOffset(years=1) - pd.Timedelta(days=1),
            end
        )

        date_ranges.append((
            current.strftime("%Y-%m-%d"),
            chunk_end.strftime("%Y-%m-%d")
        ))

        current = chunk_end + pd.Timedelta(days=1)

    chunks = [
        get_noaa_data(start, end)
        for start, end in date_ranges
    ]

    raw = pd.concat(chunks, ignore_index=True)

    if raw.empty:
        raise RuntimeError("NOAA returned no observations.")

    daily = raw.pivot_table(
        index="date",
        columns="datatype",
        values="value",
        aggfunc="first"
    ).reset_index()

    daily.columns.name = None

    # Convert precipitation from inches to millimeters
    if "PRCP" in daily.columns:
        daily["PRCP"] *= 25.4

    # Calculate temperature gradient
    if "TMAX" in daily.columns and "TMIN" in daily.columns:
        daily["Temperature_Gradient_F"] = (
            daily["TMAX"] - daily["TMIN"]
        )

    # Rename variables for readability
    daily = daily.rename(columns={
        "date": "Date",
        "TMAX": "Temperature_High_F",
        "TMIN": "Temperature_Low_F",
        "PRCP": "Precipitation_mm",
        "AWND": "Wind_Speed_mph"
    })

    daily["Date"] = pd.to_datetime(
        daily["Date"]
    ).dt.strftime("%Y-%m-%d")

    # Export to csv
    daily.to_csv(
        NOAA_OUTPUT,
        sep="\t",
        index=False,
        na_rep="NA"
    )

    return daily

# ========================== Download ERA5 Data ==========================
def download_era5(year, month):

    # Configure and check file path
    file_path = ERA5_DIR / f"era5_{year}_{month:02d}.nc"

    if file_path.exists():
        return file_path

    print(f"Downloading ERA5: {year}-{month:02d}")

    # Configrue and check days and area
    days = [
        f"{day:02d}"
        for day in range(
            1,
            calendar.monthrange(year, month)[1] + 1
        )
    ]

    area = [
        LATITUDE + 0.15,
        LONGITUDE - 0.15,
        LATITUDE - 0.15,
        LONGITUDE + 0.15
    ]

    # Configure request
    request = {
        "variable": ERA5_VARIABLES,
        "year": str(year),
        "month": f"{month:02d}",
        "day": days,
        "time": [f"{h:02d}:00" for h in range(24)],
        "area": area,
        "data_format": "netcdf",
        "download_format": "unarchived"
    }

    # Get
    cds.retrieve(
        "reanalysis-era5-land",
        request,
        str(file_path)
    )

    return file_path

# =========================== Calculate Humidity ===========================
def calculate_humidity(temperature, dewpoint):

    # Convert from kelvin to celcius
    temperature = temperature - 273.15
    dewpoint = dewpoint - 273.15

    # Calculate humidity
    humidity = 100 * np.exp(
        (17.625 * dewpoint / (243.04 + dewpoint))
        -
        (17.625 * temperature / (243.04 + temperature))
    )

    # Return humidity
    return np.clip(humidity, 0, 100)

# ======================== Process ERA5 Humidity =========================
def process_era5():

    # Configure dates
    start = pd.Timestamp(START_DATE)
    end = pd.Timestamp(END_DATE)

    months = pd.date_range(
        start=start.replace(day=1),
        end=end.replace(day=1),
        freq="MS"
    )

    hourly_data = []

    # Look through months, download file, re-arrange data into readable format
    for month in months:

        file_path = download_era5(
            month.year,
            month.month
        )

        with xr.open_dataset(file_path, engine="netcdf4") as ds:

            ds = ds.sel(
                latitude=LATITUDE,
                longitude=LONGITUDE,
                method="nearest"
            )

            temperature = (
                "t2m" if "t2m" in ds else "2m_temperature"
            )

            dewpoint = (
                "d2m" if "d2m" in ds else "2m_dewpoint_temperature"
            )

            time_name = (
                "valid_time"
                if "valid_time" in ds.coords
                else "time"
            )

            df = ds[
                [temperature, dewpoint]
            ].to_dataframe().reset_index()

            df["Humidity"] = calculate_humidity(
                df[temperature],
                df[dewpoint]
            )

            df["Date"] = (
                pd.to_datetime(df[time_name], utc=True)
                .dt.tz_convert(TIMEZONE)
                .dt.strftime("%Y-%m-%d")
            )

            hourly_data.append(
                df[["Date", "Humidity"]]
            )

    hourly = pd.concat(
        hourly_data,
        ignore_index=True
    )

    hourly = hourly[
        (hourly["Date"] >= START_DATE) &
        (hourly["Date"] <= END_DATE)
    ]

    # Calculate one daily humidity value
    daily = (
        hourly.groupby("Date")["Humidity"]
        .mean()
        .reset_index()
    )

    daily = daily.rename(columns={
        "Humidity": "Humidity_Percent"
    })

    # Output to CSV
    daily.to_csv(
        ERA5_OUTPUT,
        sep="\t",
        index=False,
        float_format="%.1f",
        na_rep="NA"
    )

    return daily

# ================================= RUN ===================================
def run_pipeline():

    print("Starting Claremont weather data collection")

    print("\nStep 1: Processing NOAA data")

    noaa = process_noaa()

    print("\nStep 2: Processing ERA5 humidity")

    humidity = process_era5()

    print("\nStep 3: Combining weather data")

    # Create complete calendar date range
    dates = pd.DataFrame({
        "Date": pd.date_range(
            START_DATE,
            END_DATE,
            freq="D"
        ).strftime("%Y-%m-%d")
    })

    # Merge NOAA and ERA5
    final = dates.merge(
        noaa,
        on="Date",
        how="left"
    )

    final = final.merge(
        humidity,
        on="Date",
        how="left"
    )

    # Final feature selection and ordering
    columns = [
        "Date",
        "Temperature_High_F",
        "Temperature_Low_F",
        "Humidity_Percent",
        "Precipitation_mm",
        "Temperature_Gradient_F",
        "Wind_Speed_mph"
    ]

    final = final[columns]

    # Round all numerical values to one decimal place
    numeric = final.select_dtypes(
        include="number"
    ).columns

    final[numeric] = final[numeric].round(1)

    # Export tab-separated TXT file
    final.to_csv(
        OUTPUT_FILE,
        sep="\t",
        index=False,
        float_format="%.1f",
        na_rep="NA"
    )

    print("DATA COLLECTION COMPLETE")

    print("NOAA Output:", NOAA_OUTPUT)
    print("ERA5 Output:", ERA5_OUTPUT)
    print("Final Output:", OUTPUT_FILE)
    print("Total days:", len(final))

    print("\nMissing values:")
    print(final.isnull().sum())

    print("\nPreview:")
    print(final.head(10).to_string(index=False))

    return final

# Run the pipeline in main
if __name__ == "__main__":

    final_data = run_pipeline()