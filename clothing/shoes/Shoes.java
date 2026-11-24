package clothing.shoes;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;

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
}
