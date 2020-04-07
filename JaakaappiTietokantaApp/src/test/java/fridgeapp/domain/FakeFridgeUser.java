
package fridgeapp.domain;

import fridgeapp.domain.FridgeUser;

public class FakeFridgeUser implements FridgeUserInterface {
    private String username;
    private String fridge;
    
    public FakeFridgeUser(String username, String fridge) {
        this.username = username;
        this.fridge = fridge;
    }

    @Override
    public String getFridge() {
        return fridge;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setFridge(String fridge) {
        this.fridge = fridge;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    
}
