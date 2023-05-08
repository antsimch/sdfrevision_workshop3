package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShoppingCartDB {
    
    public static File login(String dirPath, String userName, List<String> cart) throws IOException {
        String fileName = dirPath + File.separator + userName;
        File userFile = new File(fileName);

        if (!userFile.exists()) {
            userFile.createNewFile();
            System.out.println("Creating new user");
        }

        cart.clear();

        if (userFile.length() != 0) {
            System.out.printf("%s, your cart contains the following items\n", userName);
            FileReader fr = new FileReader(userFile);
            BufferedReader br = new BufferedReader(fr);
            String line; 
            int pos = 1;

            while ((line = br.readLine()) != null) {
                System.out.printf("%d. %s\n", pos, line);
                cart.add(line);
                pos++;
            }
        } else {
            System.out.printf("%s, your cart is empty\n", userName);
        }

        return userFile;
    }

    public static void save(File userFile, List<String> cart) throws IOException {
        if (userFile == null) {
            System.out.println("Unable to save shopping cart without user login");
            return;
        }

        FileWriter fw = new FileWriter(userFile);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for (String item : cart) {
            bw.write(item);
            bw.write("\n");
        }

        System.out.println("Your cart has been saved");

        bw.flush();
        bw.close();
        fw.close();
    }

    public static void users(File directory) {
        File[] listOfFiles = directory.listFiles();
        int pos = 1;

        System.out.println("The following users are registered");
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.printf("%d. %s\n", pos, file.getName());
                pos++;
            }
        }
    }
}
