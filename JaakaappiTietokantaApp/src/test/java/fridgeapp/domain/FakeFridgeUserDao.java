
package fridgeapp.domain;

import fridgeapp.dao.FridgeUserDao;
import java.util.ArrayList;
import java.util.List;

public class FakeFridgeUserDao implements FridgeUserDao{
    List<FridgeUser> users = new ArrayList<>();
    
    public FakeFridgeUserDao() {
        users.add(new FridgeUser("testaaja", "PakastajaElvi"));
    }
    
    @Override
    public FridgeUser create(FridgeUser user) throws Exception {
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

}
