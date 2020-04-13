
package fridgeapp.domain;

import java.util.ArrayList;
import java.util.List;

public class FridgeUser {
    private String username;
    private List<Fridge> fridges;

    public FridgeUser(String username, Fridge fridge) {
        this.username = username;
        this.fridges = new ArrayList();
        this.fridges.add(fridge);
    }
    
    public FridgeUser(String username, String fridgeName) {
        this.username = username;
        this.fridges = new ArrayList();
        this.fridges.add(new Fridge(fridgeName));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Fridge> getFridges() {
        return fridges;
    }
    
    public Fridge getDefaultFridge() {
        return this.getFridges().get(0);
    }

    public boolean addFridge(Fridge fridge) {
        if(this.fridges.contains(fridge)) {
            return false;
        } else {
            this.fridges.add(fridge);
            return true;
        }
    }
    
    public boolean addFridge(String fridgename) {
        for (Fridge fridge: this.fridges) {
            if(fridge.getFridgeName().equals(fridgename)) {
                return false;
            }
        } 
        this.fridges.add(new Fridge(fridgename));
        return true;
    }
    
    public Fridge getNextFridge(String fridgeName) {
        if(this.fridges.size() <= 1) {
            return this.fridges.get(0);
        } 
        int number=0;
        for (int i = 0; i < this.fridges.size(); i++) {
            if(this.fridges.get(i).getFridgeName().equals(fridgeName)) {
                number=i;
            }
        }    
        if(number < this.fridges.size()-1){
            return this.fridges.get(number+1);
        } else {
            return this.fridges.get(0);
        }
    }
    
    public Fridge getFridgeByFridgeName(String fridgeName) {
        for (Fridge fridge: this.fridges) {
            if (fridge.getFridgeName().equals(fridgeName)){
                return fridge;
            }
        }
        return null;
    }
     
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FridgeUser)) {
            return false;
        }
        FridgeUser other = (FridgeUser) obj;
        return username.equals(other.getUsername());
    }
    
    @Override
    public String toString() {
        return this.getUsername();
    }
    
}
