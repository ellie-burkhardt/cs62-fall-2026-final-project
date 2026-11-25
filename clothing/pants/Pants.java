package clothing.pants;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.outerwear.Outerwear;
import clothing.outerwear.OuterwearType;

public class Pants extends Clothing{
    
    // Instance variables 
    private PantsType pantsType; 

    // Constructor
    public Pants(PantsType pantsType, Fabric fabric, Weight weight, Color color, Vibe vibe) {
        super(fabric, weight, color, vibe);
        this.pantsType = pantsType;
    }

    /**
     * Getter for the type of pants
     * @return pantsType
     */
    public PantsType getPantsType() {
        return pantsType;
    }

    @Override
    public String getType() {
        return "Pants";
    }

    public static void main(String[] args){
        Fabric fabric = Fabric.COTTON;
        Vibe vibe = Vibe.FORMAL;
        Weight weight = Weight.HEAVYWEIGHT;
        Color color = Color.BLUE;
    
        Pants newpiece = new Pants(PantsType.KHAKIS,fabric,weight,color,vibe);
        System.out.println(newpiece.getType());
        System.out.println(newpiece.getPantsType());
        System.out.println(newpiece.getColor());
        System.out.println(newpiece.getFabric());
        System.out.println(newpiece.getVibe());
        System.out.println(newpiece.getWeight());

}
    
}
