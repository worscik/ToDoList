package pl.todo.User.Model;

import java.util.UUID;

public class UserDto {

    private UUID externalId;
    private String email;

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
