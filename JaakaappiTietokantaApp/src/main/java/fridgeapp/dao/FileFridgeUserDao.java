package fridgeapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.*;

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
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (FridgeUser user : users) {
                writer.write(user.getUsername() + ";" + user.getFridge() + "\n");
            }
        } 
    }
    
    @Override
    public List<FridgeUser> getAll() {
        return users;
    }
    
    @Override
    public FridgeUser findByUsername(String username) {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public FridgeUser create(FridgeUser user) throws Exception {
        users.add(user);
        save();
        return user;
    }    
}
