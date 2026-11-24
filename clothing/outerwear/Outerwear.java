package clothing.outerwear;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;

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

}
