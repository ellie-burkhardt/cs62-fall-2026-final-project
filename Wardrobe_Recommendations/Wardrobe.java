package Wardrobe_Recommendations;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

import clothing.general.Clothing;
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

            
    public static void main(String[] args){
        
    } 

   


}
