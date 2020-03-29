package fridgeapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.*;

public class FileFridgeUserDao implements fridgeUserDao {
    private List<fridgeUser> users;
    private String file;

    public FileFridgeUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                fridgeUser u = new fridgeUser(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (fridgeUser user : users) {
                writer.write(user.getUsername() + ";" + user.getName() + "\n");
            }
        } 
    }
    
    @Override
    public List<fridgeUser> getAll() {
        return users;
    }
    
    @Override
    public fridgeUser findByUsername(String username) {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public fridgeUser create(fridgeUser user) throws Exception {
        users.add(user);
        save();
        return user;
    }    
}
