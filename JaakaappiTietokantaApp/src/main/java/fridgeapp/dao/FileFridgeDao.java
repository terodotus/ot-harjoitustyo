package fridgeapp.dao;

import fridgeapp.domain.Fridge;
import fridgeapp.domain.FridgeItem;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.FridgeUser;

public class FileFridgeDao implements FridgeDao {
    public List<FridgeItem> items;
    private String file;

    public FileFridgeDao(String file, FridgeUserDao users) throws Exception {
        items = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
                FridgeUser user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                FridgeItem item = new FridgeItem(id, parts[1], done);
                items.add(item);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void saveFridge() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            
        }
    }    
    
    private int generateId() {
        return items.size() + 1;
    }
    
    @Override
    public Fridge create(Fridge fridge) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FridgeItem> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}


