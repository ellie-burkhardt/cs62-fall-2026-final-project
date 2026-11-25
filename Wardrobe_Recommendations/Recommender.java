package Wardrobe_Recommendations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Recommender {

    public static Outfit makeRecommendation(LocalDate date, WeatherLoader wl){

        List<WeatherData> Weather_Data =wl.loadFromFile("/Downloads/cs62/workspace/cs62-fall-2026-final-project/data/processed/claremont_weather_data.txt");

        WeatherData wd = wl.getData(date);

        

        
    }
}
