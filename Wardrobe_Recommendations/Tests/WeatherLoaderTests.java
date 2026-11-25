package Wardrobe_Recommendations.Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import Wardrobe_Recommendations.WeatherLoader;
import java.time.LocalDate;
import Wardrobe_Recommendations.WeatherData;

public class WeatherLoaderTests {

    private final  WeatherLoader wl = new WeatherLoader("/Users/madeleinepham/Desktop/claremont_weather_data_copy.txt" );


    @Test 
    public void WeatherLoaderTest(){
        LocalDate date = LocalDate.of(2023, 12, 5);
        WeatherData data = wl.getData(date);
        if (data == null) {
            System.out.println("No data found for " + date);
        } else {
            System.out.println(data);
            
        }
        assertEquals(data.getDate(),date);
        assertTrue(data.isRainy());

    }



        
}

