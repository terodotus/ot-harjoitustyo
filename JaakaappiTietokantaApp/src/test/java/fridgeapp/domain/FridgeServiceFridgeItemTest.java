
package fridgeapp.domain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridgeServiceFridgeItemTest {
    
    FakeFridgeItemDao fridgeItemDao;
    FakeFridgeUserDao fridgeUserDao;
    FridgeService service;
    
    public FridgeServiceFridgeItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fridgeItemDao = new FakeFridgeItemDao();
        fridgeUserDao = new FakeFridgeUserDao();
        service = new FridgeService(fridgeItemDao, fridgeUserDao);
        FridgeUser u1 = new FridgeUser("Teuvo", "Teuvonkaappi1");
        FridgeUser u2 = new FridgeUser("Tellervo", "Tellervonkaappi1");
        fridgeUserDao.create(u1);
        fridgeUserDao.create(u2);        
        try {
            fridgeItemDao.create(new FridgeItem(1, "maito", 5, u1));
        } catch (Exception ex) {
            Logger.getLogger(FridgeServiceFridgeItemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void atStartActualListContainsItems() {
        service.login("Teuvo");
        List<FridgeItem> items = service.getActualContent();
        
        assertEquals(1, items.size());
        FridgeItem item = items.get(0);
        assertEquals("maito", item.getContent());
        assertEquals("Teuvo", item.getUser().getUsername());
    }
    
    @Test
    public void listEmpytIfNotLoggedIn() {
        service.logout();
        List<FridgeItem> items = service.getActualContent();
        
        assertEquals(0, items.size());
    }    
     
    @Test
    public void loggedUsersListContainsAddedFridgeItems() {
        service.login("Teuvo");
        service.createFridgeItem("piimä", 7);
        
        List<FridgeItem> items = service.getActualContent();               
        assertEquals(2, items.size());
        FridgeItem item = items.get(1);
        
        assertEquals("piimä", item.getContent());
        assertEquals("Teuvo", item.getUser().getUsername());
    } 
    
    @Test
    public void loggedUsersListDoesNotContainItemsOfOther() {
        service.login("Teuvo");
        service.createFridgeItem("piimä", 7);
        service.logout();
        service.login("Tellervo");
        
        List<FridgeItem> items = service.getActualContent();
        assertEquals(0, items.size());
    } 
    
    @Test
    public void whenAmountisZeroNotListed() {
        service.login("Teuvo");
        service.setAmount(1, 0);
        
        List<FridgeItem> items = service.getActualContent();               
        assertEquals(0, items.size());
    }  

    @Test
    public void getLoggedIn() {
        service.login("Teuvo");
        assertEquals("Teuvo", service.getLoggedIn().getUsername());
    }
    
    @Test
    public void getLoggedInFridge() {
        service.login("Teuvo");
        assertEquals("Teuvonkaappi1", service.getLoggedInFridge().getFridgeName());
    }
    
    @Test
    public void createNewFridgeForLoggedInUser() throws Exception {
        service.login("Teuvo");
        assertTrue(service.createNewFridgeForLoggedInUser("Teuvonkaappi2"));
    }
    
    @Test
    public void createNewFridgeForLoggedInUser2() throws Exception {
        service.login("Teuvo");
        service.createNewFridgeForLoggedInUser("Teuvonkaappi2");
        assertEquals(2, service.getLoggedIn().getFridges().size());
    }
    
    @Test
    public void createNewFridgeForLoggedInUser3() throws Exception {
        service.login("Teuvo");
        service.createNewFridgeForLoggedInUser("Teuvonkaappi2");
        assertEquals("Teuvonkaappi2", service.getLoggedIn().getFridges().get(1).getFridgeName());
    }
    
    @Test
    public void createNewFridgeForLoggedInUser4() throws Exception {
        service.login("Teuvo");
        assertFalse(service.createNewFridgeForLoggedInUser("Teuvonkaappi1"));
    }
    
    @Test
    public void getByNameAndUsernameWorks() throws Exception {
        Fridge fridge = fridgeItemDao.getByUsernameAndName("Teuvo", "Teuvonkaappi1");
        assertEquals("Teuvonkaappi1", fridge.getFridgeName());
    }
    
    @Test
    public void removeLoggedInFridgeOfLoggedInUserIfOnlyOneFridgeReturnsFalse() throws Exception {
        service.login("Teuvo");
        assertFalse(service.removeLoggedInFridgeOfLoggedInUser());
    }
    
    @Test
    public void removeLoggedInFridgeOfLoggedInUserWorks() throws Exception {
        service.login("Teuvo");
        service.createNewFridgeForLoggedInUser("Teuvonkaappi2");
        assertTrue(service.removeLoggedInFridgeOfLoggedInUser());
    }
    
    
    
}
