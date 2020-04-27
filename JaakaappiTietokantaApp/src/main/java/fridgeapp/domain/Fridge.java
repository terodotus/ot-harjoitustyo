
package fridgeapp.domain;

import fridgeapp.domain.FridgeItem;
import fridgeapp.domain.FridgeUser;
import java.util.ArrayList;

/**
 * class for conceptualizing fridges
 */
public class Fridge {
    private String fridgeName;
    
    public Fridge(String fridgeName) {
        this.fridgeName = fridgeName;
    }

    public String getFridgeName() {
        return this.fridgeName;
    }

    public void setFridgeName(String fridgeName) {
        this.fridgeName = fridgeName;
    }

/**
 * method for giving String-form for fridges (fridgeName)
 */    
    public String toString() {
        return this.getFridgeName();
    }
    
}
