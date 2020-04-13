
package fridgeapp.domain;

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
}
