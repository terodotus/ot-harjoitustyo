
package fridgeapp.domain;

public class FridgeUser {
    private String name;
    private String username;

    public FridgeUser(String username, String name) {
        this.name = name;
        this.username = username;
    }
    
    public FridgeUser(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FridgeUser)) {
            return false;
        }
        FridgeUser other = (FridgeUser) obj;
        return username.equals(other.username);
    }
    
}
