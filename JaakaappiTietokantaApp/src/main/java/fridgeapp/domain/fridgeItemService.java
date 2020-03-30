
package fridgeapp.domain;
import fridgeapp.dao.fridgeDao;
import fridgeapp.dao.fridgeUserDao;

public class fridgeItemService {
    private fridgeDao fridgeDao;
    private fridgeUserDao userDao;
    private fridgeUser loggedIn;
    
    public fridgeItemService(fridgeDao fridgeDao, fridgeUserDao userDao) {
        this.userDao = userDao;
        this.fridgeDao = fridgeDao;
    }
    
    public boolean createFridgeItem(String content) {
        fridgeItem item = new fridgeItem(content, loggedIn);
        try {   
            fridgeDao.create(item);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean login(String username) {
        fridgeUser user = userDao.findByUsername(username);
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
        fridgeUser user = new fridgeUser(username, name);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    public fridgeUser getLoggedUser() {
        return loggedIn;
    }
    
     

     
}
