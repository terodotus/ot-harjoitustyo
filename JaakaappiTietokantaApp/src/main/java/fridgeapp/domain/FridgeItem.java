
package fridgeapp.domain;

import fridgeapp.domain.*;
import fridgeapp.dao.*;

public class FridgeItem {
    private int id;
    private String content;
    private int amount;
    private boolean defaultItem;
    
    public FridgeItem(){
    }
    
    public FridgeItem(int id, String content, boolean done) {
        this.id = id;
        this.content = content;
        this.defaultItem = done;
    }
    
    public FridgeItem(String content) {
        this.content = content;
        this.defaultItem = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(boolean defaultItem) {
        this.defaultItem = defaultItem;
    }
            
}
