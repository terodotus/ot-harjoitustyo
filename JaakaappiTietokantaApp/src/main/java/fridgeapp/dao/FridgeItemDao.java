
package fridgeapp.dao;

import fridgeapp.domain.Fridge;
import fridgeapp.domain.FridgeItem;
import java.util.List;

public interface FridgeItemDao {
    
    FridgeItem create(FridgeItem fridgeItem) throws Exception;
    
    List<FridgeItem> getAll();
    
    void setAmount(int id, int amount) throws Exception;
    
}
