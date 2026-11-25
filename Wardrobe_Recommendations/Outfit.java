package Wardrobe_Recommendations;

import clothing.outerwear.*;
import clothing.pants.*;
import clothing.shirts.*;
import clothing.shoes.*;
import clothing.socks.*;


public class Outfit {
    
    private Outerwear outer;
    private Pants pants;
    private Shirt shirt;
    private Shoes shoes;
    private Socks socks;

    public Outfit(Outerwear o, Pants p, Shirt s1, Shoes s2, Socks s3){
        outer = o;
        pants = p;
        shirt = s1;
        shoes = s2;
        socks = s3;
    }

    public Outerwear getOuter() { 
        return outer; 
    }

    public Pants getPants() {
        return pants; 
        }

    public Shirt getShirt() {
        return shirt; 
        }

    public Shoes getShoes() {
        return shoes; 
        }
        
    public Socks getSocks() { 
        return socks; 
    }


}
