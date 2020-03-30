/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tero
 */
public class FridgeTest {
    FridgeUser user;
    Fridge fridge;
    
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
        this.user=new FridgeUser();
        ArrayList<FridgeItem>items=new ArrayList();
        this.fridge=new Fridge(user,items);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void fridgeCreated() {
        assertFalse(this.fridge==null);
    }
}
