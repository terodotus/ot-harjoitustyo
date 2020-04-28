package fridgeapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.*;

/**
 * class for managing and saving information of FridgeUsers and their Fridges
 */
public class FileFridgeUserDao implements FridgeUserDao {
    private List<FridgeUser> users;
    private String file;

    public FileFridgeUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                FridgeUser u = new FridgeUser(parts[0], parts[1]);
                if (parts.length >= 2) {
                    for (int i = 2; i < parts.length; i++) {
                        u.addFridge(parts[i]);
                    }
                }
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }

/**
 * method for saving current users information to the file;
 */    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (FridgeUser user : users) {
                writer.write(user.getUsername() + ";");
                for (int i = 0; i < user.getFridges().size(); i++) {
                    writer.write(user.getFridges().get(i) + ";");
                }
                writer.write("\n");
            }
        } 
    }

/**
 * method for getting all users;
 */    
    @Override
    public List<FridgeUser> getAll() {
        return users;
    }

/**
 * method for getting a users by giving username;
 * @param username (String)
 * @return FridgeUser
 */      
    @Override
    public FridgeUser findByUsername(String username) {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }

/**
 * method for creating and saving a FridgeUser by giving user object;
 * @param user (FridgeUser)
 * @return user
 */      
    @Override
    public FridgeUser create(FridgeUser user) throws Exception {
        users.add(user);
        save();
        return user;
    } 

/**
 * method for updating and saving a user information by giving one user object;
 * @param user (FridgeUser)
 */      
    @Override
    public void updateUserFridges(FridgeUser user) throws Exception {
        for (FridgeUser fridgeUser: this.users) {
            if (fridgeUser.getUsername().equals(user.getUsername())) {
                for (Fridge fridge: user.getFridges()) {
                    fridgeUser.addFridge(fridge);
                }
            }
        }
        save();
    }
    
}
