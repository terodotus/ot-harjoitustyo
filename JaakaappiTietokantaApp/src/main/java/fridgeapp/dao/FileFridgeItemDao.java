
package fridgeapp.dao;

import fridgeapp.domain.*;
import fridgeapp.dao.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class for managing and saving information of FridgeItems
 */
public class FileFridgeItemDao implements FridgeItemDao {
    private List<FridgeItem> items;
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
/**
 * method to generate id for a fridgeItem;
 * @return next id number
 */    
    private int generateId() {
        return items.size() + 1;
    }

    @Override
    public void removeAlItemsFromFridge(String username, String fridgeName) {
        if (this.items.size() > 0) {
            for (int i = (this.items.size() - 1); i >= 0; i--) {
                if (this.items.get(i).getUser().getUsername().equals(username) && this.items.get(i).getFridge().getFridgeName().equals(fridgeName)) {
                    this.items.remove(i);
                }
            }
            try {
                save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
/**
 * method for saving all FridgeItems to file;
 */    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (FridgeItem item : items) {
                writer.write(item.getId() + ";" + item.getContent() + ";" + item.getAmount() + ";" + item.getUser().getUsername() + ";" + item.getFridge() + "\n");
            }
        }
    }    

/**
 * method for creating a new fridge item to file by giving it as parameter
 * @param item (FridgeItem)
 * @return FridgeItem
 */     
    @Override
    public FridgeItem create(FridgeItem item) throws Exception {
        for (FridgeItem savedItem: this.items) {
            if (savedItem.getUser().equals(item.getUser()) && savedItem.getFridge().equals(item.getFridge()) && savedItem.getContent().equals(item.getContent())) {
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

/**
 * method for getting all FridgeItems
 * @return items
 */     
    @Override
    public List<FridgeItem> getAll() {
        return items;
    }

/**
 * method for getting Fridge by username and fridgeName;
 * @param userName (String)
 * @param fridgename (String)
 * @return Fridge
 */     
    public Fridge getByUsernameAndFridgeName(String userName, String fridgename) {
        Fridge returnable = null;
        for (FridgeItem item: this.items) {
            if (item.getUser().getUsername().equals(userName) && item.getFridge().getFridgeName().equals(fridgename)) {
                returnable = item.getFridge();
            }
        }
        return returnable;
    }
/**
 * method for getting fridge number by username and fridgeName;
 * @param userName (String)
 * @param fridgename (String)
 * @return int (fridge number)
 */        
    public int getFridgeNumberByUsernameAndFridgeName(String userName, String fridgename) {
        int number = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getUser().getUsername().equals(userName) && this.items.get(i).getFridge().getFridgeName().equals(fridgename)) {
                number = i;
            }
        }
        return number;
    }

/**
 * method for setting and saving the given new amount for a fridgeItem;
 * @param id (int)
 * @param amount (int)
 */      
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
