
package fridgeapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridgeItemTest {
    private FridgeItem item1;
    private FridgeItem item2;
    private FridgeItem item3;
    private FridgeItem item4;
    private FridgeUser user;
    
    public FridgeItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        user = new FridgeUser("Elvi", "Elvinkaappi");
        item1 = new FridgeItem(1, "juusto", 1, user);
        item2 = new FridgeItem(1, "juusto", 1, user);
        item3 = new FridgeItem(2, "maito", 5, user);
        item4 = new FridgeItem(3, "piimä", 8, user);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void fridgeItemIsCreated() {
        assertFalse(item1.equals(null));
    }
    
    @Test
    public void equalWhenSameId() {
        assertTrue(item1.equals(item2));
    }
    
    @Test
    public void NotEqualWhenDifferent() {
        assertFalse(item1.equals(item3));
    }
    
    
    @Test
    public void getContentFunctions() {
        assertEquals("maito", item3.getContent());
    }
    
    @Test
    public void setContentFunctions() {
        item3.setContent("liha");
        assertEquals("liha", item3.getContent());
    }
    
    @Test
    public void setAmountFunctions() {
        item4.setAmount(10);
        assertEquals(10, item4.getAmount());
    }
    
    @Test
    public void getAmountFunctions() {
        assertEquals(8, item4.getAmount());
    }
    
    @Test
    public void getFridgeFunctions() {
        assertEquals(user.getDefaultFridge(), item4.getFridge());
    }
    
    @Test
    public void getUserFunctions() {
        assertEquals(user, item4.getUser());
    }
    
    @Test
    public void setUserFunctions() {
        FridgeUser user2 = new FridgeUser("Teppo", "Teponkaappi1");
        item4.setUser(user2);
        assertEquals(user2, item4.getUser());
    }
}
