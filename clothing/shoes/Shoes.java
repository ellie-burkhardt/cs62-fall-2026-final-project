package clothing.shoes;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.pants.Pants;
import clothing.pants.PantsType;

public class Shoes extends Clothing{
    // Instance variables 
    private ShoeType shoeType; 

    // Constructor
    public Shoes(ShoeType shoeType, Fabric fabric, Weight weight, Color color, Vibe vibe) {
        super(fabric, weight, color, vibe);
        this.shoeType = shoeType;
    }

    /**
     * Getter for the type of shoe
     * @return shoeType
     */
    public ShoeType getShoeType() {
        return shoeType;
    }

    @Override
    public String getType() {
        return "Shoe";
    }
     public static void main(String[] args){
        Fabric fabric = Fabric.COTTON;
        Vibe vibe = Vibe.FORMAL;
        Weight weight = Weight.HEAVYWEIGHT;
        Color color = Color.BLUE;
    
        Shoes newpiece = new Shoes(ShoeType.LOAFERS,fabric,weight,color,vibe);
        System.out.println(newpiece.getType());
        System.out.println(newpiece.getShoeType());
        System.out.println(newpiece.getColor());
        System.out.println(newpiece.getFabric());
        System.out.println(newpiece.getVibe());
        System.out.println(newpiece.getWeight());

}
}
