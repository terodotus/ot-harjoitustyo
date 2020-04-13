
package fridgeapp.domain;

import fridgeapp.domain.*;
import fridgeapp.dao.*;

public class FridgeItem {
    private int id;
    private String content;
    private int amount;
    private FridgeUser user;
    private Fridge fridge;
        
    public FridgeItem() {
    }
    
    public FridgeItem(String content, FridgeUser user) {
        this.content = content;
        this.user = user;
        this.amount = 0;
    }
    
    public FridgeItem(String content, int amount, FridgeUser user) {
        this.content = content;
        this.user = user;
        this.amount = amount;
    }
    
    public FridgeItem(String content, int amount, FridgeUser user, Fridge fridge) {
        this.content = content;
        this.user = user;
        this.amount = amount;
        this.fridge=fridge;
    }
    
    public FridgeItem(String content, int amount, FridgeUser user, String fridgeName) {
        this.content = content;
        this.user = user;
        this.amount = amount;
        this.fridge=user.getDefaultFridge();
    }
    
    public FridgeItem(int id, String content, FridgeUser user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }
    
    public FridgeItem(int id, String content, int amount, FridgeUser user) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.user = user;
    }
    
    public FridgeItem(int id, String content, int amount, FridgeUser user, Fridge fridge) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.user = user;
        this.fridge = fridge;
    }
    
    public FridgeItem(int id, String content, int amount, FridgeUser user, String fridgeName) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.user = user;
        this.fridge = new Fridge(fridgeName);
    }
    
    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }

    public FridgeItem(String content) {
        this.content = content;
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

    public FridgeUser getUser() {
        return user;
    }

    public void setUser(FridgeUser user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return this.getContent() + ", " + this.getAmount();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FridgeItem)) {
            return false;
        }
        FridgeItem other = (FridgeItem) obj;
        return id == other.id;
    }
    

}
