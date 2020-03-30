package fridgeapp.dao;

import java.util.List;
import fridgeapp.domain.*;

public interface FridgeUserDao {
    FridgeUser create(FridgeUser user) throws Exception;

    FridgeUser findByUsername(String username);

    List<FridgeUser> getAll();
    
}
