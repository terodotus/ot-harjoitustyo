
package fridgeapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridgeItemTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
    }
    
    @Test
    public void equalWhenSameId() {
        FridgeItem item1 = new FridgeItem(1, null, 1, null);
        FridgeItem item2 = new FridgeItem(1, null, 1, null);
        assertTrue(item1.equals(item2));
    }
}
