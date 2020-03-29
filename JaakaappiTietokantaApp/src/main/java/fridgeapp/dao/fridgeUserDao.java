package fridgeapp.dao;

import java.util.List;
import fridgeapp.domain.*;

public interface fridgeUserDao {
    fridgeUser create(fridgeUser user) throws Exception;

    fridgeUser findByUsername(String username);

    List<fridgeUser> getAll();
    
}
