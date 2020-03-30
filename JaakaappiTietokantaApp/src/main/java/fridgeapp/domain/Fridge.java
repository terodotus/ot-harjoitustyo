
package fridgeapp.domain;

import fridgeapp.domain.FridgeItem;
import fridgeapp.domain.FridgeUser;
import java.util.ArrayList;

public class Fridge {
    private ArrayList<FridgeItem>items;
    private FridgeUser user;
    
    public Fridge(FridgeUser user, ArrayList items){
        this.items=items;
        this.user=user;
    }

    public ArrayList<FridgeItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<FridgeItem> items) {
        this.items = items;
    }

    public FridgeUser getUser() {
        return user;
    }

    public void setUser(FridgeUser user) {
        this.user = user;
    }    
}
