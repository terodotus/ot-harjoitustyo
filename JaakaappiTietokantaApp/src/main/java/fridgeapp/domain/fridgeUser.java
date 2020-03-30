
package fridgeapp.domain;

public class fridgeUser {
    private String name;
    private String username;

    public fridgeUser(String username, String name) {
        this.name = name;
        this.username = username;
    }
    
    public fridgeUser(){
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
        if (!(obj instanceof fridgeUser)) {
            return false;
        }
        fridgeUser other = (fridgeUser) obj;
        return username.equals(other.username);
    }
    
}
