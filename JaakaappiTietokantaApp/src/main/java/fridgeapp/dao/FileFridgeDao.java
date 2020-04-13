package fridgeapp.dao;

import fridgeapp.domain.Fridge;
import fridgeapp.domain.FridgeItem;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fridgeapp.domain.FridgeUser;

public class FileFridgeDao implements FridgeDao {
    public List<Fridge> fridges;
    private String file;
    
    public FileFridgeDao() {
        
    }

    @Override
    public Fridge create(Fridge fridge) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Fridge> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fridge findByFidgeName(String fridgeNameame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}


