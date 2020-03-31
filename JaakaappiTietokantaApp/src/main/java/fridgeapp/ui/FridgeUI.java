
package fridgeapp.ui;

import fridgeapp.dao.FileFridgeUserDao;
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
import fridgeapp.dao.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import static javafx.application.Application.launch;
import fridgeapp.domain.*;

public class FridgeUI extends Application{
    private FridgeService service;
    
    private Scene fridgeScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private Label menuLabel = new Label();
    
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
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        Label infotext = new Label("Manage your life and your delicious items!");
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create  a new fridge user");
        
        loginButton.setOnAction(e->{
        });  
        
        createButton.setOnAction(e->{
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton, new Label(" "), infotext);       
        
        loginScene = new Scene(loginPane, 300, 250);    
   
        primaryStage.setTitle("Login Your Frigde");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
        });
    }

    @Override
    public void stop() {
      
      System.out.println("App closing!");
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
