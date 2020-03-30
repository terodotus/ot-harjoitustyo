
package fridgeapp.domain;

import fridgeapp.domain.fridgeItem;
import fridgeapp.domain.fridgeUser;
import java.util.ArrayList;

public class Fridge {
    private ArrayList<fridgeItem>items;
    private fridgeUser user;
    
    public Fridge(fridgeUser user, ArrayList items){
        this.items=items;
        this.user=user;
    }

    public ArrayList<fridgeItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<fridgeItem> items) {
        this.items = items;
    }

    public fridgeUser getUser() {
        return user;
    }

    public void setUser(fridgeUser user) {
        this.user = user;
    }
    
    
    
}
