/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Tero
 */
public class FridgeUI extends Application{

    @Override
    public void start(Stage arg0) throws Exception {
        System.out.println("Kivaa!");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop() {
      // lopetustoimenpiteet täällä
      System.out.println("App closing");
    }    
}
