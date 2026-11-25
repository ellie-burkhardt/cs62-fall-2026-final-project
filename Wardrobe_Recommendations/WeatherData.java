package Wardrobe_Recommendations;

import java.time.LocalDate;

public class WeatherData {
    private final LocalDate date;
    private final double highF;
    private final double lowF;
    private final double humidityPercent;
    private final double precipitationMm;
    private final double gradientF;
    private final double windMph;


    /**
     * Stores the WeatherData as an object
     * @param date
     * @param highF
     * @param lowF
     * @param humidityPercent
     * @param precipitationMm
     * @param gradientF
     * @param windMph
     */
    public WeatherData(LocalDate date, double highF, double lowF, double humidityPercent,
        double precipitationMm, double gradientF, double windMph) {
            this.date = date;
            this.highF = highF;
            this.lowF = lowF;
            this.humidityPercent = humidityPercent;
            this.precipitationMm = precipitationMm;
            this.gradientF = gradientF;
            this.windMph = windMph;
}

// Getter functions for each:

    public LocalDate getDate() { 
        return date; 
    }

    public double getHighF() { 
        return highF; 
    }

    public double getLowF() {
         return lowF; 
        }

    public double getHumidityPercent() {
         return humidityPercent; 
        }

    public double getPrecipitationMm() { 
        return precipitationMm; 
    
    }
    public double getGradientF() { 
        return gradientF; 

    }
    public double getWindMph() { 
        return windMph; 
    }

    public double getAverageF() { 
        return (highF + lowF) / 2.0; 
    }

    public boolean isRainy() {
         return precipitationMm >= 1.0; 
        }

    public boolean isWindy() {
         return windMph >= 15; 
        }

    @Override 
    public String toString(){
        return "WeatherData{" +
        "date=" + date +
        ", highF: " + highF +
        ", lowF: " + lowF +
        ", humidityPercent: " + humidityPercent +
        ", precipitationMm: " + precipitationMm +
        ", gradientF: " + gradientF +
        ", windMph: " + windMph +
        '}';
    }

}


