
package fridgeapp.domain;

public class fridgeItem {
    private int id;
    private String content;
    private int amount;
    private boolean defaultItem;
    private fridgeUser user;
    
    public fridgeItem(){
        
    }
    
    public fridgeItem(int id, String content, boolean done, fridgeUser user) {
        this.id = id;
        this.content = content;
        this.defaultItem = done;
        this.user = user;
    }
    
    public fridgeItem(String content, fridgeUser user) {
        this.content = content;
        this.user = user;
        this.defaultItem = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(boolean defaultItem) {
        this.defaultItem = defaultItem;
    }
    
    
        
}
