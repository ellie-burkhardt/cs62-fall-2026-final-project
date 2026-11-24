package clothing.shirts;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;

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
}
