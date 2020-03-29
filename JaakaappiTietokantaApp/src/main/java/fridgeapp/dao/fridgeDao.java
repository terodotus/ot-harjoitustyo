
package fridgeapp.dao;

import java.util.List;
import fridgeapp.domain.*;

public interface fridgeDao {
    
    fridgeItem create(fridgeItem item) throws Exception;

    List<fridgeItem> getAll();

    void setDone(int id) throws Exception;
    
}
