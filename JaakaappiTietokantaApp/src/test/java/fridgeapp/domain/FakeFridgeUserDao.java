
package fridgeapp.domain;

import fridgeapp.dao.FridgeUserDao;
import java.util.ArrayList;
import java.util.List;

public class FakeFridgeUserDao implements FridgeUserDao{
    List<FridgeUser> users = new ArrayList<>();
    
    public FakeFridgeUserDao() {
        users.add(new FridgeUser("Elvi", "Pakastajankaappi1"));
    }
    
    @Override
    public FridgeUser create(FridgeUser user) {
        users.add(user);
        return user;
    }

    @Override
    public FridgeUser findByUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public List<FridgeUser> getAll() {
        return users;
    }

    @Override
    public void updateUserFridges(FridgeUser user) throws Exception {
        for (FridgeUser fridgeUser: this.users) {
            if (fridgeUser.getUsername().equals(user.getUsername())) {
                for (Fridge fridge: user.getFridges()) {
                    fridgeUser.addFridge(fridge);
                }
            }
        }
    }

}
