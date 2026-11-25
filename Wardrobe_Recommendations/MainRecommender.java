package Wardrobe_Recommendations;
import clothing.general.Color;
import clothing.general.Fabric;
import clothing.general.Vibe;
import clothing.general.Weight;
import clothing.outerwear.Outerwear;
import clothing.outerwear.OuterwearType;
import clothing.pants.Pants;
import clothing.pants.PantsType;
import clothing.shirts.Shirt;
import clothing.shirts.ShirtType;
import clothing.shoes.Shoes;
import clothing.shoes.ShoeType;
import clothing.socks.Socks;
import clothing.socks.SocksType;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;


public class MainRecommender {

    

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


    public static void main(String[] args){
        Wardrobe newWardrobe = new Wardrobe();
        Scanner newScan = new Scanner(System.in);
        Boolean bool1 = true;
        
        // System.out.println("Choose from: \n 1: Manual Addition \n 2: Import from text file, write each item as");
        // int x = newScan.nextInt();
        // if (x == 2) {
        //     bool1 = false;
        // }
        
        // // importing from a md file


        // manual import
        while (bool1) {
            System.out.println("Pick Between: \n 0: Exit \n 1: Add Outerwear \n 2: Add Pants\n 3: Add Shirts \n 4: Add Shoes \n 5: Add Socks \n 6: Show Wardrobe \n 7: Make Outfit Recommendation");
            
            try {
                
                int i = newScan.nextInt();


            switch (i) {

                case 0 -> {
                    bool1 = false;
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

                case 6 -> {
                    newWardrobe.toString(); 
                }

                case 7 -> {

                    System.out.println("What is the date today? First Year:");
                    int year = newScan.nextInt();
                    System.out.println("Next the month (as an integer):");
                    int mo = newScan.nextInt();
                    System.out.println("And the day:");
                    int day = newScan.nextInt();

                    LocalDate date = LocalDate.of(year, mo, day);

                    Recommender.makeRecommendation(date);

                    
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

        

}
}
    

