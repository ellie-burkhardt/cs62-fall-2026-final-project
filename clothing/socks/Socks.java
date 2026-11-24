package clothing.socks;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;

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
}
