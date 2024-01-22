package pl.todo.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String login;
    private String password;
    private long id;
    private String role;

    public UserDto() {
    }

    private UserDto(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.id = builder.id;
        this.role = builder.role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static class Builder{
        private String login;
        private String password;
        private long id;
        private String role;

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
        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }
    }

}
