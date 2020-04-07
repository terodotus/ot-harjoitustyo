
package fridgeapp.domain;

public class FridgeUser implements FridgeUserInterface {
    private String username;
    private String fridge;

    public FridgeUser(String username, String fridge) {
        this.username = username;
        this.fridge = fridge;
    }
    
    public FridgeUser(){
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getFridge() {
        return fridge;
    }

    @Override
    public void setFridge(String fridge) {
        this.fridge = fridge;
    }
     
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FridgeUser)) {
            return false;
        }
        FridgeUser other = (FridgeUser) obj;
        return fridge.equals(other.fridge);
    }
    
}
