/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.dao;

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import fridgeapp.domain.*;
import fridgeapp.dao.*;

public class FileFridgeItemDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();    
  
    File userFile;  
    FridgeItemDao dao;    
    
    public FileFridgeItemDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
/*    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        FridgeUserDao userDao = new FakeFridgeUserDao();
        userDao.create(new FridgeUser("testertester", "Teppo Testaaja"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;siivoa;false;testertester\n");
        }
        
        dao = new FileFridgeItemDao(userFile.getAbsolutePath(), userDao);        
    }
*/    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {}
}
