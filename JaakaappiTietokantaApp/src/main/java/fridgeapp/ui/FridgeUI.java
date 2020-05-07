
package fridgeapp.ui;

import fridgeapp.dao.FileFridgeUserDao;
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
    
    private VBox fridgeItemSectors;
    private VBox fridgesSectors;
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
        Label amountChangeMessage = new Label();
        
        button.setOnAction(e -> {
            
            String newAmountString = newAmountInput.getText();
            
            boolean amountInputNumeric = true;
            if (newAmountString.length()==0) {
                amountInputNumeric = false;
            } else {
                for (int i=0; i < newAmountString.length(); i++) {
                    char a = newAmountString.charAt(i);
                    if (a < '0' || a > '9') {
                        amountInputNumeric = false;
                    }
                }
            }    
            
            if (amountInputNumeric==false) {
                amountChangeMessage.setText("item input illegal (%#;&)");
                amountChangeMessage.setTextFill(Color.RED);
                newAmountInput.setText("");
                
            } else { 
                int newAmount = Integer.valueOf(newAmountString);
                fridgeService.setAmount(item.getId(), newAmount);
                restoreFridge();
            }
            
        });      
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));
        box.getChildren().addAll(label, spacer, amountChangeMessage, newAmountInput, button);
        return box;
    }
    
    public void restoreFridge() {
        fridgeItemSectors.getChildren().clear();
        menuLabel.setText(fridgeService.getLoggedIn() + " logged in " + fridgeService.getLoggedInFridge());
        List<FridgeItem> actualItems = fridgeService.getActualContent();
        actualItems.forEach(item -> {
            fridgeItemSectors.getChildren().add(createFridgeSector(item));
        });     
    }
    
    public void restoreFridgeListing() {
        fridgesSectors.getChildren().clear();
        fridgesSectors.getChildren().add(new Label("Fridges"));
        List<Fridge> fridges = fridgeService.getLoggedInAllFridges();
        fridges.forEach(f -> {
            fridgesSectors.getChildren().add(new Label(f.getFridgeName()));
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

            if (fridgeService.login(username)) {
                loginMessage.setText("");
                menuLabel.setText(username + " logged in " + fridgeService.getLoggedInFridge().toString());
                restoreFridge();
                restoreFridgeListing();
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
        userCreationMessage.setText(""); 
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));
        
        Button backButton = new Button("Back");
        backButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String fridgeName = newNameInput.getText();
   
            if (fridgeName.length() < 4 || username.length() < 4 || username.contains(";") || username.contains(":") || username.contains(" ") || fridgeName.contains(";")) {
                userCreationMessage.setText("username or fridge name too short or contains illegal characters (%#;&)");
                userCreationMessage.setTextFill(Color.RED);
            
            } else if (fridgeService.createUser(username, fridgeName)) {
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton, backButton); 
        
        backButton.setOnAction(e -> {
            loginScene(primaryStage, loginMessage);
            primaryStage.setScene(loginScene);
            primaryStage.show();
            newUsernameInput.setText("");
            newNameInput.setText("");
            userCreationMessage.setText(""); 
        });  
       
        newUserScene = new Scene(newUserPane, 400, 300);
        return newUserScene;
    }
    
    @Override
    public void start(Stage primaryStage) { 
        Label loginMessage = new Label();
        loginScene(primaryStage, loginMessage);
        createNewUserScene(primaryStage, loginMessage);
        fridgeScene(primaryStage, loginMessage);
        
        primaryStage.setTitle("Your Fridge Items");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(fridgeService.getLoggedIn());
            if (fridgeService.getLoggedIn() != null) {
                e.consume();   
            }
        });
    }    

// fridge scene
    public Scene fridgeScene(Stage primaryStage, Label loginMessage) {   
        ScrollPane fridgeItemScrollbar = new ScrollPane();
        fridgeItemScrollbar.setMinWidth(500);
        
        ScrollPane fridgesScrollbar = new ScrollPane();
        
        BorderPane mainPane = new BorderPane();
        fridgeScene = new Scene(mainPane, 850, 600);
        
        Label fridgeCreationMessage = new Label();
        
        Label defaultChangeMessage = new Label();
        Label fridgeRemovalMessage = new Label();
        
        HBox menuPane = new HBox(10);
        VBox menuPane2 = new VBox(10);
        Button changeFridgeButton = new Button("open other fridge");
        Button addFridgeButton = new Button("add new fridge");
        TextField newFridgeInput = new TextField("new fridgeName");
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        Button changeDefaultFridgeButton = new Button("Make this DefaultFridge");
        Button removeFridgeButton = new Button("Remove this Fridge");
        menuPane.getChildren().addAll(menuLabel, fridgeCreationMessage, changeFridgeButton, newFridgeInput, addFridgeButton, menuSpacer, logoutButton);
        menuPane2.getChildren().addAll(changeDefaultFridgeButton, defaultChangeMessage, removeFridgeButton, fridgeRemovalMessage);
        
        Label itemCreationMessage = new Label();
        
        changeFridgeButton.setOnAction(e-> {
            if(fridgeService.nextFridgeActivate()) {
                restoreFridge();
                restoreFridgeListing();
                fridgeCreationMessage.setText("");
                itemCreationMessage.setText("");
                defaultChangeMessage.setText("");
                fridgeRemovalMessage.setText("");
                fridgeCreationMessage.setText("");
                menuLabel.setText(fridgeService.getLoggedIn() + " logged in " + fridgeService.getLoggedInFridge());
            } else {
                fridgeCreationMessage.setText("only one fridge existing");
                fridgeCreationMessage.setTextFill(Color.RED);
                restoreFridgeListing();
                itemCreationMessage.setText("");
                defaultChangeMessage.setText("");
                fridgeRemovalMessage.setText("");
            }
            
        }); 
        
        removeFridgeButton.setOnAction(e-> {
            if(fridgeService.removeLoggedInFridgeOfLoggedInUser()) {
                menuLabel.setText(fridgeService.getLoggedIn() + " logged in " + fridgeService.getLoggedInFridge());
                restoreFridge();
                restoreFridgeListing();
                fridgeCreationMessage.setText("");
                itemCreationMessage.setText("");
                defaultChangeMessage.setText("");
                fridgeRemovalMessage.setText("fridge removed");
                fridgeRemovalMessage.setTextFill(Color.GREEN);
            } else {
                fridgeRemovalMessage.setText("can not remove if only one fridge");
                fridgeRemovalMessage.setTextFill(Color.RED);
            }
        }); 
        
        changeDefaultFridgeButton.setOnAction(e-> {
            try {
                if(fridgeService.changeDefaultFridge(fridgeService.getLoggedInFridge())) {
                    restoreFridge();
                    restoreFridgeListing();
                    fridgeCreationMessage.setText("");
                    itemCreationMessage.setText("");
                    defaultChangeMessage.setText("");
                    fridgeRemovalMessage.setText("");
                } else {
                    defaultChangeMessage.setText("already default");
                    defaultChangeMessage.setTextFill(Color.GREEN);
                    itemCreationMessage.setText("");
                    fridgeCreationMessage.setText("");
                    fridgeRemovalMessage.setText("");
                    restoreFridge();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            ;
        }); 
        
        

        addFridgeButton.setOnAction(e -> {
            try {
                String newFridgeName = newFridgeInput.getText().strip();
                
                if (newFridgeName.length() < 3 || newFridgeName.contains(";") || newFridgeName.contains(":") || newFridgeName.contains(" ") || newFridgeName.contains("set fridgeName")) {
                    fridgeCreationMessage.setText("fridge name illegal (%#;&)");
                    fridgeCreationMessage.setTextFill(Color.RED);
                    itemCreationMessage.setText("");
                    
                } else if (fridgeService.createNewFridgeForLoggedInUser(newFridgeName)) {
                    fridgeCreationMessage.setText("");
                    fridgeCreationMessage.setText("new fridge created");
                    fridgeCreationMessage.setTextFill(Color.GREEN);
                    itemCreationMessage.setText("");
                    
                    
                } else {
                    fridgeCreationMessage.setText("fridge name not unique");        
                    fridgeCreationMessage.setTextFill(Color.RED);
                }
                
                newFridgeInput.setText("");
                itemCreationMessage.setText("");
                defaultChangeMessage.setText("");
                restoreFridgeListing();
                restoreFridge();
                fridgeRemovalMessage.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

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
        
        
        
        createForm.getChildren().addAll(newItemInput, newAmountInput, itemCreationMessage, createItem);
        
        fridgeItemSectors = new VBox(10);
        fridgeItemSectors.setMaxWidth(580);
        fridgeItemSectors.setMinWidth(500);
        
        fridgesSectors = new VBox(10);
        fridgesSectors.setMaxWidth(400);
        fridgesSectors.setMinWidth(10);
        
        fridgesScrollbar.setContent(fridgesSectors);
        fridgeItemScrollbar.setContent(fridgeItemSectors);
        
        restoreFridge();
        restoreFridgeListing();
        
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        mainPane.setLeft(fridgesScrollbar);
        
        mainPane.setRight(menuPane2);
        mainPane.setCenter(fridgeItemScrollbar);
        
        createItem.setOnAction(e-> {
            try {
                String newItemName = newItemInput.getText().trim();
                String newAmountInputString = newAmountInput.getText().trim();
                
                boolean amountInputNumeric = true;
                for (int i=0; i < newAmountInputString.length(); i++) {
                    char a = newAmountInputString.charAt(i);
                    if (a < '0' || a > '9') {
                        amountInputNumeric = false;
                    }
                }
                
                if (amountInputNumeric==false || newItemName.length() < 3 || newItemName.contains(";") || newItemName.contains(":") ||  newItemName.contains("set item")) {
                    itemCreationMessage.setText("item input illegal (%#;&)");
                    itemCreationMessage.setTextFill(Color.RED);
                    fridgeCreationMessage.setText("");
                    
                } else { 
                    int newItemAmount = Integer.valueOf(newAmountInputString);
                    fridgeService.createFridgeItem(newItemName, newItemAmount);
                    itemCreationMessage.setText("item created");
                    itemCreationMessage.setTextFill(Color.GREEN);
                    fridgeCreationMessage.setText("");
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
            newItemInput.setText("");
            newAmountInput.setText("");
            fridgeRemovalMessage.setText("");
           
            restoreFridgeListing();
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
