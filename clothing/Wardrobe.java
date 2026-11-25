package clothing;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import clothing.general.Clothing;
import clothing.outerwear.OuterwearType;

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
    public static void main(String args) {
        Wardrobe newWardrobe = new Wardrobe();
        Scanner newScan = new Scanner(System.in);

        

    }

}
