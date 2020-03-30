package fridgeapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.FridgeItem;
import fridgeapp.domain.FridgeUser;

public class FileFridgeDao implements FridgeItemDao {
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
    
    private void saveFridgeItem() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (FridgeItem item : items) {
                writer.write(item.getId() + ";" + item.getContent() + ";" + item.isDefaultItem() + ";"+"\n");
            }
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
    public List<FridgeItem> getAll() {
        return items;
    }
    
    @Override
    public FridgeItem create(FridgeItem item) throws Exception {
        item.setId(generateId());
        items.add(item);
        saveFridgeItem();
        return item;
    }   

    @Override
    public void setDone(int arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}


