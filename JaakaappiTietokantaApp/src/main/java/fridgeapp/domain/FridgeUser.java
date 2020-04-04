
package fridgeapp.domain;

public class FridgeUser {
    private String username;
    private String fridge;

    public FridgeUser(String username, String fridge) {
        this.username = username;
        this.fridge = fridge;
    }
    
    public FridgeUser(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFridge() {
        return fridge;
    }

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
