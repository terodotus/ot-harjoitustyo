
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

public class FileFridgeUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    FridgeUserDao dao;
    
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
            file.write("testaaja;PakastajaElvi\n");
        }
        
        dao = new FileFridgeUserDao(userFile.getAbsolutePath());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }

    @Test
    public void hello() {}
}
