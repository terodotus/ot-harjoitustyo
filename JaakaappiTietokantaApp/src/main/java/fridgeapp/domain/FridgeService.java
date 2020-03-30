
package fridgeapp.domain;
import fridgeapp.dao.*;
import java.util.ArrayList;

public class FridgeService {
    private FridgeItemDao fridgeItemDao;
    private FridgeUserDao userDao;
    private FridgeDao fridgeDao;
    private FridgeUser loggedIn;
    
    public FridgeService(FridgeItemDao fridgeItemDao, FridgeUserDao userDao) {
        this.userDao = userDao;
        this.fridgeItemDao = fridgeItemDao;
    }
    
    public boolean createFridgeItem(String content) {
        FridgeItem item = new FridgeItem(content);
        try {   
            fridgeItemDao.create(item);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean createFridge(ArrayList items) {
        Fridge fridge = new Fridge(loggedIn,items);
        try {   
            fridgeDao.create(fridge);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean login(String username) {
        FridgeUser user = userDao.findByUsername(username);
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
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        FridgeUser user = new FridgeUser(username, name);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    public FridgeUser getLoggedUser() {
        return loggedIn;
    }
    
     

     
}
