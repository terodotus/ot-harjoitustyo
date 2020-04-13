
package fridgeapp.domain;

import fridgeapp.dao.FridgeItemDao;
import java.util.ArrayList;
import java.util.List;

public class FakeFridgeItemDao implements FridgeItemDao{
    public List<FridgeItem> items;
    
    public FakeFridgeItemDao() {
        this.items = new ArrayList<>();
    }
    @Override
    public FridgeItem create(FridgeItem fridgeItem) {
        fridgeItem.setId(items.size()+1);
        this.items.add(fridgeItem);
        return fridgeItem;
    }

    @Override
    public List<FridgeItem> getAll() {
        return this.items;
    }

    @Override
    public void setAmount(int id, int amount) throws Exception {
        
        for (FridgeItem t : this.items) {
            if (t.getId() == id) {
                t.setAmount(amount);
            }
        }

    }
    
    
}
