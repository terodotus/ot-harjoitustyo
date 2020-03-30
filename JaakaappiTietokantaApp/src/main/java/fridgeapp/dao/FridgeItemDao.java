
package fridgeapp.dao;

import java.util.List;
import fridgeapp.domain.*;

public interface FridgeItemDao {
    
    FridgeItem create(FridgeItem item) throws Exception;

    List<FridgeItem> getAll();

    void setDone(int id) throws Exception;
    
}
