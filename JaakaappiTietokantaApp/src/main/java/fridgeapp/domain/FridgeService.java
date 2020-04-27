
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

/**
 * method to login with username(String); returns true if login; return false if username does not exist
 * @return true
 * @see FridgeUserDao
 */      
    public boolean login(String username) {
        FridgeUser user = fridgeUserDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        loggedInFridge = user.getDefaultFridge();
        return true;
    }

 /**
 * method to login with username(String) and fridgeName(String); returns true if login; return false if username does not exist
 * @return true
 * @see FridgeUserDao
 */       
    public boolean login(String username, String fridgeName) {
        FridgeUser user = fridgeUserDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        loggedInFridge = user.getFridgeByFridgeName(fridgeName);
        return true;
    }
/**
 * method for logout user;
 * @see FridgeUserDao
 */      
    public void logout() {
        loggedIn = null; 
        loggedInFridge = null;
    }
    
/**
 * method for creating a new user with username(String), fridgeName(String); return false if user already existing with the same username;
 * return true if successful
 * @return
 * @see FridgeUserDao
 */       
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
/**
 * method for creating a new fridge for loggedIn user with fridgeName(String); return false if user already has existing fridge with the same fridgeName;
 * return true if successful
 * @return
 * @see FridgeUserDao
 */        
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
    
/**
 * method for getting all fridgeItems of current loggedIn user and loggedIn fridge;
 * @return list of FridgeItems
 * @see FridgeUserDao
 */         
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
/**
 * method for setting amount for a fridgeItems with id and new amount as inputs;
 * @return void
 * @see FridgeUserDao
 */     
    public void setAmount(int id, int newAmount) {
        try {
            fridgeItemDao.setAmount(id, newAmount);
        } catch (Exception ex) {
        }
    } 

}
