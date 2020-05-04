
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

    public List<Fridge> getLoggedInAllFridges() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return loggedIn.getFridges();
    }

    public boolean removeLoggedInFridgeOfLoggedInUser() {
        if (loggedIn.getFridges().size() > 1) {
            String removableFridgeName = loggedInFridge.getFridgeName();
            this.nextFridgeActivate();
            fridgeItemDao.removeAlItemsFromFridge(loggedIn.getUsername(), removableFridgeName);
            this.nextFridgeActivate();
            loggedIn.removeFridge(removableFridgeName);
            try {
                fridgeUserDao.updateUserFridges(loggedIn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
    
/**
 * method returns current loggedInFridge of loggedInUser
 * @return loggedInFridge
 */    
    public Fridge getLoggedInFridge() {
        return loggedInFridge;
    }

/**
 * method for changing loggedIn users default fridge
 */       
    public boolean changeDefaultFridge(Fridge fridge) throws Exception {
        if (loggedIn.changeDefaultFridge(fridge)) {
            fridgeUserDao.updateUserFridges(loggedIn);
            return true;
        } else {
            return false;
        }
    }

/**
 * method returns true if there is more than one fridge and also activates that to loggedIn;
 * returns false if only one fridge existing
 * @return Boolean
 */        
    public boolean nextFridgeActivate() {
        
        if (loggedIn.getFridges().size() > 1) {
            Fridge nextFridge = loggedIn.getNextFridge(loggedInFridge.getFridgeName());
            this.loggedInFridge = nextFridge;
            try {
                fridgeUserDao.updateUserFridges(loggedIn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
        
    }
/**
 * method created a new fridgeItem if not yet existing in fridge with same name; returns true if creates
 * @return true
 * @param content (String)
 * @param amount (int)
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
 * @param username (String)
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
 * @param username (String)
 * @param fridgeName (String)
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
 * @return true if successful
 * @param username (String)
 * @param fridgeName (String)
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
 * @return true if successful
 * @param fridgeName (String)
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
 * @param id (int)
 * @param newAmount (int)
 * @see FridgeUserDao
 */     
    public void setAmount(int id, int newAmount) {
        try {
            fridgeItemDao.setAmount(id, newAmount);
        } catch (Exception ex) {
        }
    } 

}
