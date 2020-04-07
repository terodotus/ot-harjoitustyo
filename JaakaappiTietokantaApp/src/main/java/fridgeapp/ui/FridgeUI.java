
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

public class FridgeUI extends Application {
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
        Label label  = new Label(item.toString());
        label.setMinHeight(28);
        Button button = new Button("set amount");
        TextField newAmountInput = new TextField();
        button.setOnAction(e -> {
            int newAmount = Integer.valueOf(newAmountInput.getText());
            if (newAmount < 0) {
                newAmount = 0;
            }
            fridgeService.setAmount(item.getId(), newAmount);
            restoreFridge();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));
        
        box.getChildren().addAll(label, spacer, newAmountInput, button);
        return box;
    }
    
    public void restoreFridge() {
        fridgeSectors.getChildren().clear();     

        List<FridgeItem> actualItems = fridgeService.getActualContent();
        actualItems.forEach(item -> {
            fridgeSectors.getChildren().add(createFridgeSector(item));
        });     
    }
    
    public Scene loginScene(Stage primaryStage, Label loginMessage) {
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        
        Label infotext = new Label("Manage your life and your delicious items!");
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create  a new fridge user");
        
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText().trim();
            menuLabel.setText(username + " logged in...");
            if (fridgeService.login(username)) {
                loginMessage.setText("");
                restoreFridge();
                primaryStage.setScene(fridgeScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene); 
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton, new Label(" "), infotext);       
        
        loginScene = new Scene(loginPane, 400, 300);  
        return loginScene;
    }
    
    public Scene createNewUserScene(Stage primaryStage, Label loginMessage) {
        
        VBox newUserPane = new VBox(10);
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField(); 
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
     
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Your Fridge Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
   
            if (username.length() < 2 || name.length() < 2) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);  
            } 
            if (username.contains(";") || username.contains(":") || username.contains(" ") || name.contains(";")) {
                userCreationMessage.setText("username or fridge name contains illegal characters (%#;&)");
                userCreationMessage.setTextFill(Color.RED);
            
            } else if (fridgeService.createUser(username, name)) {
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 400, 300);
        return newUserScene;
    }
    
    @Override
    public void start(Stage primaryStage) { 
        Label loginMessage = new Label();
        loginScene(primaryStage, loginMessage);
        createNewUserScene(primaryStage, loginMessage);
        createFridgeScene(primaryStage, loginMessage);
        
        primaryStage.setTitle("Your Fridge Items");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(fridgeService.getLoggedUser());
            if (fridgeService.getLoggedUser() != null) {
                e.consume();   
            }
            
        });
    }    

// fridge scene
    public Scene createFridgeScene(Stage primaryStage, Label loginMessage) {   
        ScrollPane fridgeItemScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(fridgeItemScollbar);
        fridgeScene = new Scene(mainPane, 500, 300);
                
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e-> {
            fridgeService.logout();
            primaryStage.setScene(loginScene);
        });        
        
        HBox createForm = new HBox(10);    
        Button createItem = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newItemInput = new TextField("set item");
        TextField newAmountInput = new TextField("set amount");
        createForm.getChildren().addAll(newItemInput, newAmountInput, spacer, createItem);
        
        fridgeSectors = new VBox(10);
        fridgeSectors.setMaxWidth(480);
        fridgeSectors.setMinWidth(480);
        restoreFridge();
        
        fridgeItemScollbar.setContent(fridgeSectors);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        
        createItem.setOnAction(e-> {
            fridgeService.createFridgeItem(newItemInput.getText().trim(), Integer.valueOf(newAmountInput.getText().trim()));
            newItemInput.setText(""); 
            newAmountInput.setText("");
            restoreFridge();
        });
        return fridgeScene;
    }

    @Override
    public void stop() {
      
      System.out.println("App closing!");
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
