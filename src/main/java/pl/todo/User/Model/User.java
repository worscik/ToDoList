package pl.todo.User.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String login;
    private String password;
    private long id;

    public User() {

    }

    private User(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.id = builder.id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class Builder{
        private String login;
        private String password;
        private long id;

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

}
