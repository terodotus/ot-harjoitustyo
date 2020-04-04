/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.dao;

import fridgeapp.domain.*;
import fridgeapp.dao.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tero
 */
public class FileFridgeItemDao implements FridgeItemDao{
    public List<FridgeItem> items;
    private String file;
    
    public FileFridgeItemDao(String file, FridgeUserDao users) throws Exception {
        items = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                
                int amount = Integer.valueOf(parts[2]);
                FridgeUser user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                FridgeItem item = new FridgeItem(id, parts[1], user);
                items.add(item);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private int generateId() {
        return items.size() + 1;
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (FridgeItem item : items) {
                writer.write(item.getId() + ";" + item.getContent() + ";" + item.getAmount() + ";" + item.getUser().getUsername() + "\n");
            }
        }
    }    

    @Override
    public FridgeItem create(FridgeItem item) throws Exception {
        item.setId(generateId());
        items.add(item);
        save();
        return item;
    }   

    @Override
    public List<FridgeItem> getAll() {
        return items;
    }

    @Override
    public void setAmount(int id, int amount) throws Exception {
        for (FridgeItem t : items) {
            if (t.getId() == id) {
                t.setAmount(amount);
            }
        }
        save();
    }
    
}
