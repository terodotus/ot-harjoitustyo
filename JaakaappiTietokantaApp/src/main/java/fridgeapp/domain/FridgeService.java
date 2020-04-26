
package fridgeapp.domain;

import fridgeapp.dao.*;
import fridgeapp.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class for actions in fridges, fridgeItems, and users
 */
public class FridgeService {
    private FridgeUserDao fridgeUserDao;
    private FridgeItemDao fridgeItemDao;
    private FridgeUser loggedIn;
    private Fridge loggedInFridge;
    
    public FridgeService(FridgeItemDao fridgeItemDao, FridgeUserDao userDao) {
        this.fridgeUserDao = userDao;
        this.fridgeItemDao = fridgeItemDao;
    }
    
 /**
 * method returns current loggedIn user
 * @return loggedIn
 */
    public FridgeUser getLoggedIn() {
        return loggedIn;
    }

/**
 * method returns current loggedInFridge of loggedInUser
 * @return loggedInFridge
 */    
    public Fridge getLoggedInFridge() {
        return loggedInFridge;
    }

    public void setLoggedInFridge(Fridge loggedInFridge) {
        this.loggedInFridge = loggedInFridge;
    }

/**
 * method returns current next fridge of loggedInUser from fridges list of that user
 * @return loggedIn
 */        
    public Fridge nextFridgeActivate() {
        Fridge nextFridge = loggedIn.getNextFridge(loggedInFridge.getFridgeName());
        return nextFridge;
    }
/**
 * method created a new fridgeItem if not yet existing in fridge with same name; returns true if creates
 * @return true
 * @see FridgeItemDao
 */          
    public boolean createFridgeItem(String content, int amount) {
        FridgeItem item = new FridgeItem(content, amount, loggedIn, loggedInFridge);
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
        loggedInFridge = user.getDefaultFridge();
        return true;
    }
    
    public boolean login(String username, String fridgeName) {
        FridgeUser user = fridgeUserDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        loggedInFridge = user.getFridgeByFridgeName(fridgeName);
        return true;
    }
    
    public void logout() {
        loggedIn = null; 
        loggedInFridge = null;
    }
    
    public boolean createUser(String username, String fridgeName)  {   
        if (fridgeUserDao.findByUsername(username) != null) {
            return false;
        }
        FridgeUser user = new FridgeUser(username, fridgeName);
        try {
            fridgeUserDao.create(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean createNewFridgeForLoggedInUser(String fridgeName) throws Exception {
        for (Fridge fridge: loggedIn.getFridges()) {
            if (fridge.getFridgeName().equals(fridgeName)) {
                return false;
            }
        }
        loggedIn.addFridge(fridgeName);
        fridgeUserDao.updateUserFridges(loggedIn);
        return true;
    }
     
    public List<FridgeItem> getActualContent() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
          
        return fridgeItemDao.getAll()
            .stream()
            .filter(t -> t.getUser().equals(loggedIn))
            .filter(t -> t.getFridge().equals(loggedInFridge))
            .filter(t -> t.getAmount() > 0)
            .collect(Collectors.toList());
    }
   
    public void setAmount(int id, int newAmount) {
        try {
            fridgeItemDao.setAmount(id, newAmount);
        } catch (Exception ex) {
        }
    } 

}
