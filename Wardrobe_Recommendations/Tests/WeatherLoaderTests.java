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
        LocalDate date2 = LocalDate.of(2023,12,22);
        LocalDate date3 = LocalDate.of(2024,3,12);
        
        WeatherData data = wl.getData(date);
        WeatherData data2 = wl.getData(date2);
        WeatherData data3 = wl.getData(date3);
    
        assertEquals(data.getDate(),date);
        assertFalse(data.isRainy());
        assertEquals(84.0, data.getHighF());
        assertEquals(50.0, data.getLowF());
        assertEquals(42.6, data.getHumidityPercent());
        assertEquals(0.0, data.getPrecipitationMm());
        assertEquals(34.0, data.getGradientF());
        assertEquals(2.0, data.getWindMph());
        assertFalse(data.isWindy());

        assertTrue(data2.isRainy());
        assertTrue(data3.isWindy());


    }



        
}

