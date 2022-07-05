package todo.list.LoginVerify;

import org.apache.catalina.User;

public class UserLogin {

    private String email;
    private String password;
    private UserToken userToken;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

//    public UserLogin(String email, String password, UserToken userToken) {
//        this.email = email;
//        this.password = password;
//        this.userToken = userToken;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserToken getUserToken() {
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }
}
