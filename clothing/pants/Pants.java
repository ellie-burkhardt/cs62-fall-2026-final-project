package clothing.pants;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;

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

}
