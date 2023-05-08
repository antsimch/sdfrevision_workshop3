package sg.edu.nus.iss;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        String dirPath = "db";

        if (args.length > 0) {
            dirPath = args[0];
        }

        File newDir = new File(dirPath);

        if (!newDir.exists()) {
            newDir.mkdir();
        }

        File userFile = null;

        Scanner scan = new Scanner(System.in);
        String option = "";
        String input = "";
        List<String> cart = new ArrayList<>();

        System.out.println("Welcome to your shopping cart");
        
        while (!"exit".equals(option)) {
            System.out.print(">");
            option = scan.next().toLowerCase();
            input = scan.nextLine().toLowerCase();
    
            switch (option) {
                case "login" -> userFile = ShoppingCartDB.login(dirPath, input, cart);  
                case "save" -> ShoppingCartDB.save(userFile, cart);
                case "users" -> ShoppingCartDB.users(newDir);
                case "list" -> list(cart);
                case "add" -> add(cart, input);
                case "delete" -> delete(cart, input);
                case "exit" -> System.out.println("See you again!");
                default -> System.out.println("Available options are list, add, delete & exit");
            }
        }

        scan.close();
    }

    public static void list(List<String> cart) {
        if (!cart.isEmpty()) {
            int index = 0;
            for (String item: cart) {
                index++;  
                System.out.println(index + ". " + item);
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }

    public static void add(List<String> cart, String input) {
        if (!input.isEmpty()) {
            String[] items = input.split(",");
            for (String item : items) {
                if (!cart.contains(item.trim().toLowerCase())) {
                    cart.add(item.trim().toLowerCase());
                    System.out.println(item.trim().toLowerCase() + " added to cart");
                } else {
                    System.out.println("You have " + item.trim().toLowerCase() + " in your cart");
                }
            }
        } else {
            System.out.println("No items entered");
        }
    }

    public static void delete(List<String> cart, String input) {
        if (!input.isEmpty()) {
            int index = Integer.parseInt(input.trim());
            if ((index > 0) && (index < cart.size())) {
                System.out.println(cart.get(index - 1) + " removed from cart");
                cart.remove(index - 1);
            } else {
                System.out.println("Incorrect item index");
            }
        } else {
            System.out.println("No index entered");
        }
    }
}
