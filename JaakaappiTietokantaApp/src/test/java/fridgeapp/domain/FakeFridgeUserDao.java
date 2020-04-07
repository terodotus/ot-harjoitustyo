/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.domain;

import fridgeapp.dao.FridgeItemDao;
import fridgeapp.dao.FridgeUserDao;
import java.util.List;

/**
 *
 * @author Tero
 */
public class FakeFridgeUserDao implements FridgeUserDao{

    @Override
    public FridgeUser create(FridgeUser user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FridgeUser findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FridgeUser> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
