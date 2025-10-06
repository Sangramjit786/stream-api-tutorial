package net.javaguides.sys.design.messenger;

public class User {

    private String userId;
    private String username;
    // other user attributes

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        // initialize other attributes
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
