
package fridgeapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * class for conceptualizing fridgeUsers
 */
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
/**
 * method for getting all fridges as a list from this.user
 * @return all fridges as a list
 */   
    public List<Fridge> getFridges() {
        return fridges;
    }

/**
 * method for getting this.users first fridge
 * @return this.users first fridge
 */      
    public Fridge getDefaultFridge() {
        return this.getFridges().get(0);
    }

/**
 * method for changing given fridge to the default fridge, so first fridge on the fridge list;
 */      
    public void changeDefaultFridge(Fridge fridge) {
        List<Fridge> newFridgeList = new ArrayList();
        newFridgeList.add(fridge);
        for(Fridge f: this.getFridges()) {
            if(!f.getFridgeName().equals(fridge.getFridgeName())) {
                newFridgeList.add(f);
            }
        }
        this.fridges = newFridgeList;
    }

/**
 * method for adding a new fridge for this.user fridge(Fridge) as input
 * @param fridge (Fridge)
 * @return true if successful, false if fridge with same name already existing
 */    
    public boolean addFridge(Fridge fridge) {
        if (this.fridges.contains(fridge)) {
            return false;
        } else {
            this.fridges.add(fridge);
            return true;
        }
    }
    
    public boolean removeLastFridge() {
        if(this.getFridges().size() < 2) {
            return false;
        } else {
            int removable = this.fridges.size()-1;
            this.fridges.remove(removable);
            return true;
        }
    }
    
    public boolean removeFridge(String fridgename) {
        if(this.getFridges().size() < 2) {
            return false;
        } else {
            int removable = this.fridges.size()+2;
            for(int i = 0; i < this.getFridges().size(); i++) {
                if (this.fridges.get(i).getFridgeName().equals(fridgename)) {
                   removable = i; 
                }
            }
            if(removable != this.fridges.size()+2) {
                this.fridges.remove(removable);
            }
            return true;
        }
    }

/**
 * method for adding a new fridge for this.user fridgeName(String) as input
 * @param fridgeName(String)
 * @return true if successful, false if fridge with same name already existing
 */      
    public boolean addFridge(String fridgeName) {
        for (Fridge fridge: this.fridges) {
            if (fridge.getFridgeName().equals(fridgeName)) {
                return false;
            }
        } 
        this.fridges.add(new Fridge(fridgeName));
        return true;
    }

/**
 * method for getting the next fridge from this.users fridge-list; fridgeName(String) as input
 * @param fridgeName (String)
 * @return the next fridge from the list (or the same fridge if only one existing)
 */     
    public Fridge getNextFridge(String fridgeName) {
        if (this.fridges.size() <= 1) {
            return this.fridges.get(0);
        } 
        int number = 0;
        for (int i = 0; i < this.fridges.size(); i++) {
            if (this.fridges.get(i).getFridgeName().equals(fridgeName)) {
                number = i;
            }
        }    
        if (number < this.fridges.size() - 1) {
            return this.fridges.get(number + 1);
        } else {
            return this.fridges.get(0);
        }
    }
/**
 * method for getting the fridge from this.users fridge-list with fridgeName(String) as input
 * @param fridgeName (String)
 * @return the fridge from the list with that fridgeName which was given
 */       
    public Fridge getFridgeByFridgeName(String fridgeName) {
        for (Fridge fridge: this.fridges) {
            if (fridge.getFridgeName().equals(fridgeName)) {
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
