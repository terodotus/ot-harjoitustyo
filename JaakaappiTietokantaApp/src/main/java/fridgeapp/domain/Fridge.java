
package fridgeapp.domain;

import fridgeapp.domain.FridgeItem;
import fridgeapp.domain.FridgeUser;
import java.util.ArrayList;

public class Fridge {
    private String fridgeName;
    
    public Fridge(String fridgeName) {
        this.fridgeName=fridgeName;
    }

    public String getFridgeName() {
        return fridgeName;
    }

    public void setFridgeName(String fridgeName) {
        this.fridgeName = fridgeName;
    }
    
    public String toString() {
        return this.getFridgeName();
    }
    
}
