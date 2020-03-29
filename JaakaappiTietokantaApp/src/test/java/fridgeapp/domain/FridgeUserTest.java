/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.domain;

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
public class FridgeUserTest {
    fridgeUser user1;
    
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
        user1=new fridgeUser("sepotus", "Seppo");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void userIsCreated() {
        assertEquals("Seppo", user1.getName());
    }
}
