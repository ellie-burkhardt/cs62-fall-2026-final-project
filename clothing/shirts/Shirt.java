package clothing.shirts;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.pants.Pants;
import clothing.pants.PantsType;

public class Shirt extends Clothing {

    // Instance variables 
    private ShirtType shirtType; 

    // Constructor
    public Shirt(ShirtType shirtType, Fabric fabric, Weight weight, Color color, Vibe vibe) {
        super(fabric, weight, color, vibe);
        this.shirtType = shirtType;
    }

    /**
     * Getter for the type of shirt
     * @return shirtType
     */
    public ShirtType getShirtType() {
        return shirtType;
    }

    @Override
    public String getType() {
        return "Shirt";
    }

     public static void main(String[] args){
        Fabric fabric = Fabric.COTTON;
        Vibe vibe = Vibe.FORMAL;
        Weight weight = Weight.HEAVYWEIGHT;
        Color color = Color.BLUE;
    
        Shirt newpiece = new Shirt(ShirtType.BUTTON_UP,fabric,weight,color,vibe);
        System.out.println(newpiece.getType());
        System.out.println(newpiece.getShirtType());
        System.out.println(newpiece.getColor());
        System.out.println(newpiece.getFabric());
        System.out.println(newpiece.getVibe());
        System.out.println(newpiece.getWeight());

}
}
