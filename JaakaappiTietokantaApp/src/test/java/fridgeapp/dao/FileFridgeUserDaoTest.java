
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
import fridgeapp.domain.FridgeUser;
import java.util.List;

public class FileFridgeUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    FridgeUserDao fridgeUserDao;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
  
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("testaaja;TestaajanKaappi1\n");
        }
        
        fridgeUserDao = new FileFridgeUserDao(userFile.getAbsolutePath());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void fridgeUsersAreReadCorrectlyFromFile() {
        List<FridgeUser> users = fridgeUserDao.getAll();
        assertEquals(1, users.size());
        FridgeUser user = users.get(0);
        assertEquals("TestaajanKaappi1", user.getDefaultFridge().getFridgeName());
        assertEquals("testaaja", user.getUsername());
    }
    
    @Test
    public void existingFridgeUserIsFound() {
        FridgeUser user = fridgeUserDao.findByUsername("testaaja");
        assertEquals("TestaajanKaappi1", user.getDefaultFridge().getFridgeName());
        assertEquals("testaaja", user.getUsername());
    }
    
    @Test
    public void savedFridgeUserIsFound() throws Exception {
        FridgeUser newUser = new FridgeUser("Tero", "Teronkaappi1");
        fridgeUserDao.create(newUser);
        
        FridgeUser user = fridgeUserDao.findByUsername("Tero");
        assertEquals("Teronkaappi1", user.getDefaultFridge().getFridgeName());
        assertEquals("Tero", user.getUsername());
    }
    
    @Test
    public void nonExistingFridgeUserIsFound() {
        FridgeUser user = fridgeUserDao.findByUsername("Tapio");
        assertEquals(null, user);
    }
    
}
