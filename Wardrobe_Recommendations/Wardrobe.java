package Wardrobe_Recommendations;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

import clothing.general.Clothing;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.outerwear.Outerwear;
import clothing.outerwear.OuterwearType;
import clothing.pants.Pants;
import clothing.pants.PantsType;
import clothing.pants.Pants;
import clothing.shirts.Shirt;
import clothing.shirts.ShirtType;
import clothing.shoes.Shoes;
import clothing.shoes.ShoeType;
import clothing.socks.Socks;
import clothing.socks.SocksType;
import java.lang.StringBuilder;



public class Wardrobe {

    // Instance variable
    private HashMap<String, ArrayList<Clothing>> clothes;

    // Constructor
    public Wardrobe() {
        clothes = new HashMap<>();
    }

    /**
     * Add clothing items to the HashMap
     * 
     * @param clothing
     */
    public void addClothing (Clothing clothing){
        String type = clothing.getType(); 

        // Add a new array list if the type of clothing is not present in the HashMap
        // BONUS: fun little .putIfAbsent method from HashMap documentation
        clothes.putIfAbsent(type, new ArrayList<>());
        clothes.get(type).add(clothing);

    }

    /**
     * Removes clothing from the HashMap if it exists
     * 
     * @param clothing
     */
    public void removeClothing(Clothing clothing){
        String type = clothing.getType(); 

        // Check to make sure that the HashMap contains the clothing item
        if (clothes.containsKey(type)){
            // Remove
            clothes.get(type).remove(clothing);
        }
    }

    /**
     * Returns an ArrayList of all the clothes of a particular type
     * 
     * @param type
     * @return the ArrayList of the clothing type
     */
    public ArrayList<Clothing> getClothing(String type) {
        if (clothes.containsKey(type)) {
            return clothes.get(type);
        }

        return new ArrayList<>();
    }

    /**
     * Returns the total number of clothes
     * @return number of clothes
     */
    public int getTotalClothing() {
        int total = 0;

        for (ArrayList<Clothing> list : clothes.values()) {
            total += list.size();
        }

        return total;
    }

    /**    
     * 
     * toString method that prints out all clothes separated by category
     */
    @Override 
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String newline = System.lineSeparator();
        sb.append("Total number of clothing items :" + this.getTotalClothing() + "\n");
        
        for (HashMap.Entry<String, ArrayList<Clothing>> entry : clothes.entrySet()) {
            sb.append(entry.getKey()).append(":").append(newline);

            for (Clothing item : entry.getValue()) {
                sb.append("- ").append(item).append(newline);
            }
        }

        return sb.toString();
    
    }

            


    /**
     * Helper function for adding types using enumeration classes
     * @param <T>
     * @param scan
     * @param enumClass
     * @param prompt
     * @return
     */
    private static <T extends Enum<T>> T pickEnum(Scanner scan, Class<T> enumClass, String prompt) {
        T[] options = enumClass.getEnumConstants(); // from the Class documentation
        while (true) {
            System.out.println(prompt);
            for (int k = 0; k < options.length; k++) {
                System.out.println((k + 1) + ": " + options[k]);
            }
            int choice = scan.nextInt();
            if (choice >= 1 && choice <= options.length) {
                return options[choice - 1];
            }
            System.out.println("Invalid choice, try again.");
        }
    }


    /**
     * Initiates the 
     * @param args
     */
    public static void main(String[] args) {
        Wardrobe newWardrobe = new Wardrobe();
        Scanner newScan = new Scanner(System.in);
        Boolean addClothes = true;
        Boolean makeRecomendation = false;
        
        // System.out.println("Choose from: \n 1: Manual Addition \n 2: Import from text file, write each item as");
        // int x = newScan.nextInt();
        // if (x == 2) {
        //     bool1 = false;
        // }
        
        // // importing from a md file


        // manual import
        while (addClothes) {
            System.out.println("Add item? Pick a # from: \n 0: Exit \n 1: Outerwear \n2: Pants\n3: Shirts\n4: Shoes\n5: Socks" );
            
            try {
                
                int i = newScan.nextInt();


            switch (i) {

                case 0 -> {
                    addClothes = false;
                }

                case 1 -> {
                    OuterwearType type = pickEnum(newScan, OuterwearType.class, "Pick a type:");
                    Fabric fabric = pickEnum(newScan, Fabric.class, "Pick a fabric:");
                    Weight weight = pickEnum(newScan, Weight.class, "Pick a weight:");
                    Color color = pickEnum(newScan, Color.class, "Pick a color:");
                    Vibe vibe = pickEnum(newScan, Vibe.class, "Pick a vibe:");
                    newWardrobe.addClothing(new Outerwear(type, fabric, weight, color, vibe));
                }

                case 2 -> {
                    PantsType type = pickEnum(newScan, PantsType.class, "Pick a type:");
                    Fabric fabric = pickEnum(newScan, Fabric.class, "Pick a fabric:");
                    Weight weight = pickEnum(newScan, Weight.class, "Pick a weight:");
                    Color color = pickEnum(newScan, Color.class, "Pick a color:");
                    Vibe vibe = pickEnum(newScan, Vibe.class, "Pick a vibe:");
                    newWardrobe.addClothing(new Pants(type, fabric, weight, color, vibe));
                }
                case 3 -> {
                    ShirtType type = pickEnum(newScan, ShirtType.class, "Pick a type:");
                    Fabric fabric = pickEnum(newScan, Fabric.class, "Pick a fabric:");
                    Weight weight = pickEnum(newScan, Weight.class, "Pick a weight:");
                    Color color = pickEnum(newScan, Color.class, "Pick a color:");
                    Vibe vibe = pickEnum(newScan, Vibe.class, "Pick a vibe:");
                    newWardrobe.addClothing(new Shirt(type, fabric, weight, color, vibe));
                }
                case 4 -> {
                    ShoeType type = pickEnum(newScan, ShoeType.class, "Pick a type:");
                    Fabric fabric = pickEnum(newScan, Fabric.class, "Pick a fabric:");
                    Weight weight = pickEnum(newScan, Weight.class, "Pick a weight:");
                    Color color = pickEnum(newScan, Color.class, "Pick a color:");
                    Vibe vibe = pickEnum(newScan, Vibe.class, "Pick a vibe:");
                    newWardrobe.addClothing(new Shoes(type, fabric, weight, color, vibe));
                }
                case 5 -> {
                    SocksType type = pickEnum(newScan, SocksType.class, "Pick a type:");
                    Fabric fabric = pickEnum(newScan, Fabric.class, "Pick a fabric:");
                    Weight weight = pickEnum(newScan, Weight.class, "Pick a weight:");
                    Color color = pickEnum(newScan, Color.class, "Pick a color:");
                    Vibe vibe = pickEnum(newScan, Vibe.class, "Pick a vibe:");
                    newWardrobe.addClothing(new Socks(type, fabric, weight, color, vibe));
                }
                default -> {
                    System.out.println("Invalid Choice! Try again.");
                }

            }
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a number!");
                newScan.nextLine();   

            }

                  
    }
        newWardrobe.toString(); 

        System.out.println("What is the date today?");
        int date = newScan.nextInt();

        

}
}
