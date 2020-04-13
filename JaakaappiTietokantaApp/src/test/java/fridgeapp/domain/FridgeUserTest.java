
package fridgeapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridgeUserTest {
    FridgeUser user1;
    FridgeUser user2;
    FridgeUser user3;
    
    public FridgeUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        user1=new FridgeUser("Seppo", "seponkaappi");
        user2=new FridgeUser("Teppo", "teponkaappi");
        user3=new FridgeUser("Teppo", "teponkaappi");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void userIsCreated() {
        assertFalse(user1.equals(null));
    }
    
    @Test
    public void getUserNameWorking() {
        assertEquals("Seppo", user1.getUsername());
    }
    
    @Test
    public void setUserNameWorking() {
        user1.setUsername("Matti");
        assertEquals("Matti", user1.getUsername());
    }
    
    @Test
    public void equalWhenSameUsername() {
        assertTrue(user2.equals(user3));
    }
    
    @Test
    public void getFridgeWorking() {
        assertEquals("seponkaappi", user1.getFridges().get(0).toString());
    }
    
    

}
