
package fridgeapp.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FridgeTest {
    Fridge fridge1;
    Fridge fridge2;
    
    public FridgeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fridge1=new Fridge("JohnsFridge1");
        fridge2=new Fridge("ElvinKaappi2");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void fridgeCreated() {
        assertFalse(fridge1.equals(null));
    }
    
    @Test
    public void toStringWorks() {
        assertEquals("ElvinKaappi2", fridge2.toString());
    }
    
    @Test
    public void setFridgeNameWorks() {
        fridge1.setFridgeName("WilliamsFridge");
        assertEquals("WilliamsFridge", fridge1.toString());
    }
    
    @Test
    public void getFridgeNameWorks() {
        assertEquals("JohnsFridge1", fridge1.toString());
    }
    
}
