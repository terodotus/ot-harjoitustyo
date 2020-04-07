
package fridgeapp.domain;

import fridgeapp.dao.FridgeItemDao;
import java.util.List;

public class FakeFridgeItemDao implements FridgeItemDao{

    @Override
    public FridgeItem create(FridgeItem fridgeItem) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FridgeItem> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAmount(int id, int amount) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
