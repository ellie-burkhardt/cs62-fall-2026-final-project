package clothing.socks;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.pants.Pants;
import clothing.pants.PantsType;

public class Socks extends Clothing{
    // Instance variables 
    private SocksType socksType; 

    // Constructor
    public Socks(SocksType socksType, Fabric fabric, Weight weight, Color color, Vibe vibe) {
        super(fabric, weight, color, vibe);
        this.socksType = socksType;
    }

    /**
     * Getter for the type of Socks
     * @return socksType
     */
    public SocksType getSocksType() {
        return socksType;
    }

    @Override
    public String getType() {
        return "Sock";
    }
     public static void main(String[] args){
        Fabric fabric = Fabric.COTTON;
        Vibe vibe = Vibe.FORMAL;
        Weight weight = Weight.HEAVYWEIGHT;
        Color color = Color.BLUE;
    
        Socks newpiece = new Socks(SocksType.COMPRESSION,fabric,weight,color,vibe);
        System.out.println(newpiece.getType());
        System.out.println(newpiece.getSocksType());
        System.out.println(newpiece.getColor());
        System.out.println(newpiece.getFabric());
        System.out.println(newpiece.getVibe());
        System.out.println(newpiece.getWeight());

}
}
