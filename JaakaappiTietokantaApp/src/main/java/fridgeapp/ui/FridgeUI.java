
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
        actualItems.forEach(item->{
            fridgeSectors.getChildren().add(createFridgeSector(item));
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
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if (fridgeService.login(username) ){
                loginMessage.setText("");
                restoreFridge();
                primaryStage.setScene(fridgeScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("use does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  

        createButton.setOnAction(e->{
            usernameInput.setText("");
            primaryStage.setScene(newUserScene); 
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton, new Label(" "), infotext);       
        
        loginScene = new Scene(loginPane, 300, 250);  
        
        // new createNewUserScene
        
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
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e->{
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
   
            if ( username.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);  
            } 
            if (username.contains(";")||username.contains(":")){
                userCreationMessage.setText("username contains illegal characters (%#;&)");
                userCreationMessage.setTextFill(Color.RED);
            
            } else if (fridgeService.createUser(username, name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        // main scene
        
        ScrollPane todoScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(todoScollbar);
        fridgeScene = new Scene(mainPane, 300, 250);
                
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            fridgeService.logout();
            primaryStage.setScene(loginScene);
        });        
        
        HBox createForm = new HBox(10);    
        Button createItem = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newItemInput = new TextField();
        createForm.getChildren().addAll(newItemInput, spacer, createItem);
        
        fridgeSectors = new VBox(10);
        fridgeSectors.setMaxWidth(280);
        fridgeSectors.setMinWidth(280);
        restoreFridge();
        
        todoScollbar.setContent(fridgeSectors);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        
        createItem.setOnAction(e->{
            fridgeService.createFridgeItem(newItemInput.getText());
            newItemInput.setText("");       
            restoreFridge();
        });
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 300, 250);
   
        // seutp primary stage
        
        primaryStage.setTitle("Todos");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
            System.out.println(fridgeService.getLoggedUser());
            if (fridgeService.getLoggedUser()!=null) {
                e.consume();   
            }
            
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
