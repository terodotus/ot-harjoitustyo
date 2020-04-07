
package fridgeapp.domain;

public interface FridgeUserInterface {

    boolean equals(Object obj);

    String getFridge();

    String getUsername();

    void setFridge(String fridge);

    void setUsername(String username);
    
}
