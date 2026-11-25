package Wardrobe_Recommendations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import clothing.general.Clothing;
import clothing.general.Vibe;
import clothing.outerwear.*;
import clothing.pants.*;
import clothing.shirts.*;
import clothing.shoes.*;
import clothing.socks.*;


public class Recommender {

    private ArrayList<Clothing> outerlist;
    private ArrayList<Clothing> pantslist;
    private ArrayList<Clothing> shirtlist;
    private ArrayList<Clothing> shoeslist;
    private ArrayList<Clothing> sockslist;

    public Outfit makeRecommendation(LocalDate date, WeatherLoader wl, Vibe v, Wardrobe w){

        List<WeatherData> Weather_Data =wl.loadFromFile("/Downloads/cs62/workspace/cs62-fall-2026-final-project/data/processed/claremont_weather_data.txt");

        WeatherData wd = wl.getData(date);

        Outfit returnedOutfit;


        //store each type of clothes in their own list
       outerlist = w.getClothing("outerwear");
       pantslist = w.getClothing("pants");
       shirtlist = w.getClothing("shirt");
       shoeslist = w.getClothing("shoes");
       sockslist = w.getClothing("socks");

        // remove all that aren't matching the vibe
       for (Clothing c : outerlist) {
        if (!(c.getVibe().equals(v))){
            w.removeClothing(c);
        }
       }
       for (Clothing c : pantslist) {
        if (!(c.getVibe().equals(v))){
            w.removeClothing(c);
        }
       }
       for (Clothing c : shirtlist) {
        if (!(c.getVibe().equals(v))){
            w.removeClothing(c);
        }
       }
       for (Clothing c : shoeslist) {
        if (!(c.getVibe().equals(v))){
            w.removeClothing(c);
        }
       }
       for (Clothing c : sockslist) {
        if (!(c.getVibe().equals(v))){
            w.removeClothing(c);
        }
       }


        





        
    }
}
