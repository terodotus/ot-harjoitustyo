
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
    private FridgeService fridgeService;
    
    private Scene fridgeScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox fridgeSectors;
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String fridgeItemFile = properties.getProperty("fridgeItemFile");
            
        FileFridgeUserDao fridgeUserDao = new FileFridgeUserDao(userFile);
        FileFridgeItemDao fridgeItemDao = new FileFridgeItemDao(fridgeItemFile, fridgeUserDao);
        fridgeService = new FridgeService(fridgeItemDao, fridgeUserDao);
    }
    
    public Node createFridgeSector(FridgeItem item) {
        HBox box = new HBox(10);
        Label label  = new Label(item.getContent());
        label.setMinHeight(28);
        Button button = new Button("set amount to zero");
        int newAmount=0;
        button.setOnAction(e->{
            fridgeService.setAmount(item.getId(),newAmount);
            restoreFridge();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
    
    public void restoreFridge() {
        fridgeSectors.getChildren().clear();     

        List<FridgeItem> actualItems = fridgeService.getActualContent();
        actualItems.forEach(todo->{
            fridgeSectors.getChildren().add(createFridgeSector(todo));
        });     
    }
    
    @Override
    public void start(Stage primaryStage) {               
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("myFridge");
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
