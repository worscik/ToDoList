package pl.todo.User.Model;

public class UserIdRequest {

    private long userId;

    public UserIdRequest() {
    }

    public UserIdRequest(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
