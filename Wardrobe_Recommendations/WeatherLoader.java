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

    /**
     * 
     * @param filePath
     * @return WeatherData parsed into a List of WeatherData objects
     */
    public static List<WeatherData> loadFromFile(String filePath) {
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
                System.out.println("Skipping Line: " + line);
            }
                  
        }
        return readings;
    }
}
