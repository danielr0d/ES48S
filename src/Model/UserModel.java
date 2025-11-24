package Model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String email;

    public UserModel() {
        this.id = 0;
    }

    public UserModel(String username, String email) {
        this.id = 0;
        this.username = username;
        this.email = email;
    }

    public UserModel(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
