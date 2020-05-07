
package fridgeapp.dao;

import fridgeapp.domain.Fridge;
import fridgeapp.domain.FridgeItem;
import java.util.List;

/**
 * Interface for managing FridgeItems
 */
public interface FridgeItemDao {
    
    FridgeItem create(FridgeItem fridgeItem) throws Exception;
    
    List<FridgeItem> getAll();
    
    void removeAlItemsFromFridge(String username, String fridgeName);
    
    void setAmount(int id, int amount) throws Exception;
    
    Fridge getByUsernameAndFridgeName (String userName, String fridgename);
    
}
