
package fridgeapp.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridgeServiceFridgeUserTest {
    
    FakeFridgeItemDao fridgeItemDao;
    FakeFridgeUserDao fridgeUserDao;
    FridgeService service;
    
    public FridgeServiceFridgeUserTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void loggedInUserCanLogout() {
        service.login("Elvi");
        service.logout();
        
        assertEquals(null, service.getLoggedIn());
    } 
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("Elvi");
        assertTrue(result);
        
        FridgeUser loggedIn = service.getLoggedIn();
        assertEquals("Elvi", loggedIn.getUsername() );
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = service.login("nonexisting");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedIn());
    }  
    
    @Test
    public void nextFridgeActivateWorks() {
        service.login("Elvi");
        try {
            service.createNewFridgeForLoggedInUser("Pakastajankaappi2");
        } catch (Exception ex) {
            Logger.getLogger(FridgeServiceFridgeUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        service.nextFridgeActivate();
        assertEquals("Pakastajankaappi2", service.getLoggedIn().getFridges().get(1).getFridgeName());
    }
    
    @Test
    public void userCanBeAdded() {
        assertTrue(service.createUser("Helvi", "Helvinkaappi1"));
        service.login("Helvi");
        assertEquals("Helvi", service.getLoggedIn().getUsername());
    }
    
    @Test
    public void changeDefaultFridgeWorks() {
        assertTrue(service.createUser("Helvi", "Helvinkaappi1"));
        service.login("Helvi");
        try {
            assertTrue(service.createNewFridgeForLoggedInUser("Helvinkaappi2"));
        } catch (Exception ex) {
            Logger.getLogger(FridgeServiceFridgeUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        service.nextFridgeActivate();
        try {
            service.changeDefaultFridge(service.getLoggedInFridge());
        } catch (Exception ex) {
            Logger.getLogger(FridgeServiceFridgeUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("Helvinkaappi2", service.getLoggedIn().getDefaultFridge().getFridgeName());
    }
    
    @Test
    public void getAllFridgesOfLoggedInWorks() {
        assertTrue(service.createUser("Helvi", "Helvinkaappi1"));
        service.login("Helvi");
        try {
            assertTrue(service.createNewFridgeForLoggedInUser("Helvinkaappi2"));
        } catch (Exception ex) {
            Logger.getLogger(FridgeServiceFridgeUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(2, service.getLoggedInAllFridges().size());
    }
    
}
