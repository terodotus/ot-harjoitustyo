
package fridgeapp.domain;

import fridgeapp.dao.FridgeItemDao;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FakeFridgeItemDao implements FridgeItemDao{
    public List<FridgeItem> items;
    
    public FakeFridgeItemDao() {
        this.items = new ArrayList<>();
    }
    
    @Override
    public FridgeItem create(FridgeItem item) throws Exception {
        for (FridgeItem savedItem: this.items) {
            if (savedItem.getUser().equals(item.getUser()) && savedItem.getFridge().equals(item.getFridge()) && savedItem.getContent().equals(item.getContent())) {
                int newAmount = savedItem.getAmount() + item.getAmount();
                if (newAmount < 0) {
                    newAmount = 0;
                }
                savedItem.setAmount(newAmount);
                
                return savedItem;
            } 
        }
        item.setId(generateId());
        items.add(item);
        
        return item;
    } 
    
    private int generateId() {
        return items.size() + 1;
    }

    @Override
    public List<FridgeItem> getAll() {
        return this.items;
    }

    @Override
    public void setAmount(int id, int amount) throws Exception {
        
        for (FridgeItem t : this.items) {
            if (t.getId() == id) {
                t.setAmount(amount);
            }
        }

    }
    
    public Fridge getByUsernameAndName(String userName, String fridgename) {
        Fridge returnable = null;
        for (FridgeItem item: this.items) {
            if (item.getUser().getUsername().equals(userName) && item.getFridge().getFridgeName().equals(fridgename)) {
                returnable = item.getFridge();
            }
        }
        return returnable;
    }

    @Override
    public void removeAlItemsFromFridge(String username, String fridgeName) {
        if (this.items.size() > 0) {
            for (int i = (this.items.size() - 1); i >= 0; i--) {
                if (this.items.get(i).getUser().getUsername().equals(username) && this.items.get(i).getFridge().getFridgeName().equals(fridgeName)) {
                    this.items.remove(i);
                }
            }
        }
    }
    
    public Fridge getByUsernameAndFridgeName(String userName, String fridgename) {
        Fridge returnable = null;
        for (FridgeItem item: this.items) {
            if (item.getUser().getUsername().equals(userName) && item.getFridge().getFridgeName().equals(fridgename)) {
                returnable = item.getFridge();
            }
        }
        return returnable;
    }
    
    
}
