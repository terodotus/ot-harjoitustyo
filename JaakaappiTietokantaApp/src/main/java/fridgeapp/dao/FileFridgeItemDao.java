
package fridgeapp.dao;

import fridgeapp.domain.*;
import fridgeapp.dao.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileFridgeItemDao implements FridgeItemDao {
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
                FridgeUser user = users.getAll().stream().filter(u -> u.getUsername().equals(parts[3])).findFirst().orElse(null);
                Fridge fridge = user.getFridgeByFridgeName(parts[4]);
                FridgeItem item = new FridgeItem(id, parts[1], amount, user, fridge);
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
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (FridgeItem item : items) {
                writer.write(item.getId() + ";" + item.getContent() + ";" + item.getAmount() + ";" + item.getUser().getUsername() + ";" + item.getFridge() + "\n");
            }
        }
    }    

    @Override
    public FridgeItem create(FridgeItem item) throws Exception {
        for (FridgeItem savedItem: this.items) {
            if (savedItem.getUser().equals(item.getUser()) && savedItem.getFridge().equals(item.getFridge())&& savedItem.getContent().equals(item.getContent())) {
                int newAmount = savedItem.getAmount() + item.getAmount();
                if (newAmount < 0) {
                    newAmount = 0;
                }
                savedItem.setAmount(newAmount);
                save();
                return savedItem;
            } 
        }
        item.setId(generateId());
        items.add(item);
        save();
        return item;
    }   

    @Override
    public List<FridgeItem> getAll() {
        return items;
    }
    
    public Fridge getByUsernameAndName(String userName, String fridgename) {
        Fridge returnable = null;
        for (FridgeItem item: this.items) {
            if(item.getUser().getUsername().equals(userName) && item.getFridge().getFridgeName().equals(fridgename)) {
                returnable = item.getFridge();
            }
        }
        return returnable;
    }
    
    public int getFridgeNumberByUsernameAndName(String userName, String fridgename) {
        int number=0;
        for (int i = 0; i < this.items.size(); i++) {
            if(this.items.get(i).getUser().getUsername().equals(userName) && this.items.get(i).getFridge().getFridgeName().equals(fridgename)) {
                number=i;
            }
        }
        return number;
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
