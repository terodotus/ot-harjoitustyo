
package fridgeapp.domain;

import fridgeapp.domain.*;
import fridgeapp.dao.*;

/**
 * class for conceptualizing fridgeItems
 */
public class FridgeItem {
    private int id;
    private String content;
    private int amount;
    private FridgeUser user;
    private Fridge fridge;
 
    public FridgeItem(String content, int amount, FridgeUser user, Fridge fridge) {
        this.content = content;
        this.user = user;
        this.amount = amount;
        this.fridge = fridge;
    }
    
    public FridgeItem(int id, String content, int amount, FridgeUser user) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.user = user;
        this.fridge = user.getDefaultFridge();
    }
    
    public FridgeItem(int id, String content, int amount, FridgeUser user, Fridge fridge) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.user = user;
        this.fridge = fridge;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
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
    
/**
 * method for giving String-form for fridgeItems ("content, amount")
 * @return fridgeItem as String ("content, amount")
 */    
    @Override
    public String toString() {
        return this.getContent() + ", " + this.getAmount();
    }

/**
 * method returning the equivalence of fridgeItems
 * @param obj (other object)
 * @return if the objects are the same
 */        
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FridgeItem)) {
            return false;
        }
        FridgeItem other = (FridgeItem) obj;
        return id == other.id;
    }
    

}
