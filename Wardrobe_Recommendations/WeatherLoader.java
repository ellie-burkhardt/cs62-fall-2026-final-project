package Wardrobe_Recommendations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WeatherLoader{

    /**
     * 
     * @param filePath
     * @return WeatherData parsed into a List of WeatherData objects
     */
    public static List<WeatherData> loadFromFile(String filePath) {
        List<WeatherData> readings = new ArrayList<>();

        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Could not read weather file: " + e.getMessage());
            return readings;
        }
    }
}
