
package fridgeapp.domain;

import fridgeapp.dao.*;
import fridgeapp.domain.*;
import java.util.ArrayList;

public class FridgeService {
    private FridgeUserDao fridgeUserDao;
    private FridgeItemDao fridgeItemDao;
    private FridgeUser loggedIn;
    
    public FridgeService(FridgeItemDao fridgeItemDao, FridgeUserDao userDao) {
        this.fridgeUserDao = userDao;
        this.fridgeItemDao = fridgeItemDao;
    }
    
    public boolean createFridgeItem(String content) {
        FridgeItem item = new FridgeItem(content, loggedIn);
        try {   
            fridgeItemDao.create(item);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean login(String username) {
        FridgeUser user = fridgeUserDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }
    
    public void logout() {
        loggedIn = null;  
    }
    
     public boolean createUser(String username, String name)  {   
        if (fridgeUserDao.findByUsername(username) != null) {
            return false;
        }
        FridgeUser user = new FridgeUser(username, name);
        try {
            fridgeUserDao.create(user);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    public FridgeUser getLoggedUser() {
        return loggedIn;
    }
    
     

     
}
