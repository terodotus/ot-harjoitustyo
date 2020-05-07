
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
        user1=new FridgeUser("Seppo", "seponkaappi1");
        user2=new FridgeUser("Teppo", "teponkaappi1");
        user3=new FridgeUser("Teppo", "teponkaappi1");
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
    public void notEqualWhenDifferent() {
        assertFalse(user1.equals(user2));
    }
    
    @Test
    public void getFridgeWorking() {
        assertEquals("seponkaappi1", user1.getFridges().get(0).toString());
    }
    
    @Test
    public void addFridgeStringWorking() {
        user1.addFridge("seponkaappi2");
        assertEquals(2, user1.getFridges().size());
    }
    
    @Test
    public void addFridgeStringWorking2() {
        user1.addFridge("seponkaappi2");
        assertEquals("seponkaappi2", user1.getFridges().get(1).getFridgeName());
    }
    
    @Test
    public void addFridgeFridgeWorking() {
        user1.addFridge(new Fridge("seponkaappi2"));
        assertEquals(2, user1.getFridges().size());
    }
    
    @Test
    public void addFridgeFridgeWorking2() {
        user1.addFridge(new Fridge("seponkaappi2"));
        assertEquals("seponkaappi2", user1.getFridges().get(1).getFridgeName());
    }

    @Test
    public void getNextFridgeWorking() {
        Fridge seponToinenFridge = new Fridge("seponkaappi2");
        user1.addFridge(seponToinenFridge);
        assertEquals(seponToinenFridge, user1.getNextFridge("seponkaappi1"));
    }
    
    @Test
    public void getNextFridgeWorking2() {
        Fridge seponToinenFridge = new Fridge("seponkaappi2");
        user1.addFridge(seponToinenFridge);
        assertEquals("seponkaappi1", user1.getNextFridge("seponkaappi2").getFridgeName());
    }
    
    @Test
    public void getFridgeByFridgeNameWorking() {
        Fridge seponToinenFridge = new Fridge("seponkaappi2");
        user1.addFridge(seponToinenFridge);
        assertEquals(seponToinenFridge, user1.getFridgeByFridgeName("seponkaappi2"));
    }
    
    @Test
    public void getFridgeByFridgeNameWorking2() {
        Fridge seponToinenFridge = new Fridge("seponkaappi2");
        user1.addFridge(seponToinenFridge);
        assertEquals(null, user1.getFridgeByFridgeName("teponkaappi2"));
    }
    
    @Test
    public void toStringWorking() {
        assertEquals("Seppo", user1.toString());
    }
    
    @Test
    public void removeFridgeRemovesFridge() {
        user1.addFridge(new Fridge("seponkaappi2"));
        user1.removeFridge("seponkaappi1");
        assertEquals(1, user1.getFridges().size());
    }
    
    @Test
    public void removeFridgeDoesNotRemoveFinalFridge() {
        assertFalse(user1.removeFridge("seponkaappi1"));
        assertEquals(1, user1.getFridges().size());
    }
    
    @Test
    public void changeDefaultFridgeWorks() {
        Fridge fridge1 = new Fridge("seponkaappi2");
        user1.addFridge(fridge1);
        assertTrue(user1.changeDefaultFridge(fridge1));
        assertEquals(fridge1, user1.getDefaultFridge());
    }
    
    @Test
    public void changeDefaultFridgeReturnsFalseIfOnlyOneFridge() {
        Fridge fridge1 = user1.getDefaultFridge();
        assertFalse(user1.changeDefaultFridge(fridge1));
    }
    
}
