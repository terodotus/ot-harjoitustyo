package fridgeapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.fridgeItem;
import fridgeapp.domain.fridgeUser;

public class FileFridgeDao implements fridgeDao {
    public List<fridgeItem> items;
    private String file;

    public FileFridgeDao(String file, fridgeUserDao users) throws Exception {
        items = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
                fridgeUser user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                fridgeItem item = new fridgeItem(id, parts[1], done, user);
                items.add(item);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (fridgeItem item : items) {
                writer.write(item.getId() + ";" + item.getContent() + ";" + item.isDefaultItem() + ";" + item.getUser().getUsername() + "\n");
            }
        }
    }    
    
    private int generateId() {
        return items.size() + 1;
    }
    
    @Override
    public List<fridgeItem> getAll() {
        return items;
    }
    
    @Override
    public fridgeItem create(fridgeItem item) throws Exception {
        item.setId(generateId());
        items.add(item);
        save();
        return item;
    }   

    @Override
    public void setDone(int arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}


