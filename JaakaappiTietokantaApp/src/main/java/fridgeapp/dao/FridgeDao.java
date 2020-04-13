
package fridgeapp.dao;

import fridgeapp.domain.Fridge;
import fridgeapp.domain.FridgeItem;
import fridgeapp.domain.FridgeUser;
import java.util.List;

public interface FridgeDao {
    
    Fridge create(Fridge fridge) throws Exception;
    
    Fridge findByFidgeName(String fridgeNameame);
    
    List<Fridge> getAll();
}
