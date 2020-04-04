
package fridgeapp.domain;

import fridgeapp.domain.*;
import fridgeapp.dao.*;

public class FridgeItem {
    private int id;
    private String content;
    private int amount;
    private FridgeUser user;
        
    public FridgeItem(){
    }
    
    public FridgeItem(int id, String content,FridgeUser user) {
        this.id = id;
        this.content = content;
        this.user=user;
    }
    
    public FridgeItem(int id, String content,int amount,FridgeUser user) {
        this.id = id;
        this.content = content;
        this.amount=amount;
        this.user=user;
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
    public boolean equals(Object obj) {
        if (!(obj instanceof FridgeItem)) {
            return false;
        }
        FridgeItem other = (FridgeItem) obj;
        return id == other.id;
    }
    

}
