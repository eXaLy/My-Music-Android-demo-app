package ly.aswin.mymusic.data.models;

/**
 * Created by Aswin on 14-7-16
 */
public class User {
    private String username;
    private String userId;

    public User(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
}
