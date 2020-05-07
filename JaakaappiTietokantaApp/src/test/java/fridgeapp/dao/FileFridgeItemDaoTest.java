
package fridgeapp.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import fridgeapp.domain.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FileFridgeItemDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();    
  
    File userFile;  
    FridgeItemDao fridgeItemDao;    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        FridgeUserDao fridgeUserDao = new FakeFridgeUserDao();
        fridgeUserDao.create(new FridgeUser("testaaja", "TestaajanKaappi1"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;maito;9;testaaja;TestaajanKaappi1\n");
        }
        
        fridgeItemDao = new FileFridgeItemDao(userFile.getAbsolutePath(), fridgeUserDao);        
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void fridgeItemAmountCanBeSet() throws Exception {
        fridgeItemDao.setAmount(1, 100);
        FridgeItem item = fridgeItemDao.getAll().get(0);
        assertEquals(100, item.getAmount());
    } 
    
    @Test
    public void fridgeItemsAreReadCorrectlyFromFile() {
        List<FridgeItem> items = fridgeItemDao.getAll();
        assertEquals(1, items.size());
        FridgeItem item = items.get(0);
        assertEquals("maito", item.getContent());
        assertEquals(9, item.getAmount());
        assertEquals(1, item.getId());
        assertEquals("testaaja", item.getUser().getUsername());
        assertEquals("TestaajanKaappi1", item.getFridge().getFridgeName());
    }    
    
    @Test
    public void createdFridgeItemsListed() throws Exception {    
        fridgeItemDao.create(new FridgeItem(2, "mehu", 3, new FridgeUser("testaaja2", "Testaaja2nKaappi1")));
        
        List<FridgeItem> items = fridgeItemDao.getAll();
        assertEquals(2, items.size());
        FridgeItem item = items.get(1);
        assertEquals("mehu", item.getContent());
        assertEquals(3, item.getAmount());
        assertNotEquals(1, item.getId());
        assertEquals("testaaja2", item.getUser().getUsername());
    }   
    
    @Test
    public void createdFridgeItemsAreNotCreatedAgain() throws Exception {  
        FridgeItem testedItem = new FridgeItem(2, "mehu", 3, new FridgeUser("testaaja2", "Testaaja2nKaappi1"));
        fridgeItemDao.create(testedItem);
        List<FridgeItem> items = fridgeItemDao.getAll();
        assertEquals(2, items.size());
        fridgeItemDao.create(testedItem);
        assertEquals(2, items.size());
    }  
    
    @Test
    public void removeAllItemsFromFridgeWorks() {
        fridgeItemDao.removeAlItemsFromFridge("testaaja", "TestaajanKaappi1");
        assertEquals(0, fridgeItemDao.getAll().size());
    }
    
    @Test
    public void getByUsernameAndFridgeNameReturnsRight() {
        assertEquals("TestaajanKaappi1", fridgeItemDao.getByUsernameAndFridgeName("testaaja", "TestaajanKaappi1").getFridgeName());
    }
    
    @Test
    public void getFridgeNumberByUsernameAndFridgeNameReturnsRight() {
        assertEquals(0, fridgeItemDao.getFridgeNumberByUsernameAndFridgeName("testaaja", "TestaajanKaappi1"));
    }
    
}
