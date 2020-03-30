package fridgeapp;

import fridgeapp.ui.FridgeUI;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        System.out.println("Hello! Welcome to the fridge! Please, give your userID. Thanks!");
        String userID=reader.nextLine();
        System.out.println("Welcome!");
        
    }
    
/*    
    public static void main(String[] args) {
        FridgeUI.main(args);
    }
*/    
}
