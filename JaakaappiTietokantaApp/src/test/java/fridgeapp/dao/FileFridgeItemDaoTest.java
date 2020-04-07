
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
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FileFridgeItemDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();    
  
    File userFile;  
    FridgeItemDao dao;    
    
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
        fridgeUserDao.create(new FridgeUser("testaaja", "Pakastaja Elvi"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;maito;9;testaaja\n");
        }
        
        dao = new FileFridgeItemDao(userFile.getAbsolutePath(), fridgeUserDao);        
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void hello() {}
}
