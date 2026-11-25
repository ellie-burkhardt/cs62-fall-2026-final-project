package Wardrobe_Recommendations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class WeatherLoader{

    private final List<WeatherData> readings;

    public WeatherLoader(String filePath) {
        this.readings = loadFromFile(filePath);   // your existing static method
    }

    /**
     * 
     * @param filePath
     * @return WeatherData parsed into a List of WeatherData objects
     */
    public List<WeatherData> loadFromFile(String filePath) {
        List<WeatherData> readings = new ArrayList<>();


        // First store all lines
        List<String> lines; 
        try { 
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Could not read weather file: " + e.getMessage());
            return readings;
        }

        // Now parse by line
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            // Skip blank lines
            if (line.isBlank()) { 
                continue;
            }

            // split by two or more spaces "\\s+"
            String[] p = line.trim().split("\\s+");  
            // what to do with the data that doesn't have certain items?

            try {
                readings.add(new WeatherData(
                    LocalDate.parse(p[0]),
                    Double.parseDouble(p[1]),
                    Double.parseDouble(p[2]),
                    Double.parseDouble(p[3]),
                    Double.parseDouble(p[4]),
                    Double.parseDouble(p[5]),
                    Double.parseDouble(p[6])));
                
    
                }
            catch (NumberFormatException | NullPointerException | DateTimeException e) { // what kind of errors do you get here
                
            }
                  
        }
        return readings;

       
    }

    /**
     * 
     * @param date
     * @return WeatherData object for the given data
     */
    public WeatherData getData(LocalDate date){
        for (WeatherData w : readings){
            if (w.getDate().equals(date)){
                return w;
            }
        }
        return null;

    }

    public static void main(String[] args){
        WeatherLoader wl = new WeatherLoader("/Users/madeleinepham/Desktop/claremont_weather_data_copy.txt" );

        LocalDate date = LocalDate.of(2023, 12, 5);

        WeatherData data = wl.getData(date);
        if (data == null) {
            System.out.println("No data found for " + date);
        } else {
            System.out.println(data);
}
    }
}
