package clothing.outerwear;

import clothing.general.Clothing;
import clothing.general.ClothingType;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.pants.Pants;
import clothing.pants.PantsType;

public class Outerwear extends Clothing{
    
    // Instance variables 
    private OuterwearType outerwearType; 

    // Constructor
    public Outerwear(OuterwearType outerwearType, Fabric fabric, Weight weight, Color color, Vibe vibe) {
        super(fabric, weight, color, vibe);
        this.outerwearType = outerwearType;
    }

    /**
     * Getter for the type of outerwear
     * @return outerwearType
     */
    public OuterwearType getOuterwearType() {
        return outerwearType;
    }

    @Override
    public String getType() {
        return "Outerwear";
    }


    public static void main(String[] args){
        Fabric fabric = Fabric.COTTON;
        Vibe vibe = Vibe.FORMAL;
        Weight weight = Weight.HEAVYWEIGHT;
        Color color = Color.BLUE;
    
        Outerwear newpiece = new Outerwear(OuterwearType.COAT,fabric,weight,color,vibe);
        System.out.println(newpiece.getType());
        System.out.println(newpiece.getOuterwearType());
        System.out.println(newpiece.getColor());
        System.out.println(newpiece.getFabric());
        System.out.println(newpiece.getVibe());
        System.out.println(newpiece.getWeight());

}
}