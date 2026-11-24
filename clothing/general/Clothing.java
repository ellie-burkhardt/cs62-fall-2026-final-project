package clothing.general;

public abstract class Clothing {
    
    // Instance variables 
    // private ClothingType type;
    private Fabric fabric;
    private Weight weight;
    private Color color;
    private Vibe vibe;

    // Constructor
    public Clothing(Fabric fabric, Weight weight, Color color, Vibe vibe) {
        // this.type = type;
        this.fabric = fabric;
        this.weight = weight;
        this.color = color;
        this.vibe = vibe;
    }

    public abstract String getType();

    /**
     * Gets clothing fabric
     * @return clothing fabric
     */
    public Fabric getFabric() {
        return fabric;
    }

    /**
     * Gets clothing weight 
     * @return clothing weight
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * Gets clothing color
     * @return clothing color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets clothing vibe 
     * @return clothing vibe
     */
    public Vibe getVibe() {
        return vibe;
    }

}
