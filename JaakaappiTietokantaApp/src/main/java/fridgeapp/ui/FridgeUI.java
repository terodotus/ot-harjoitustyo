
package fridgeapp.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import fridgeapp.dao.fridgeUserDao;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import static javafx.application.Application.launch;
import fridgeapp.domain.*;

public class FridgeUI extends Application{
    private fridgeItemService service;
    
    private Scene fridgeScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    
    @Override
    public void init() throws Exception {
        
    }
    
    
    @Override
    public void start(Stage primaryStage) {               
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        // new createNewUserScene
        
        VBox newUserPane = new VBox(10);
        
        
        // seutp primary stage
        
        primaryStage.setTitle("Items");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
            System.out.println(service.getLoggedUser());
            if (service.getLoggedUser()!=null) {
                e.consume();   
            }
            
        });
    }

    @Override
    public void stop() {
      // tee lopetustoimenpiteet täällä
      System.out.println("sovellus sulkeutuu");
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
