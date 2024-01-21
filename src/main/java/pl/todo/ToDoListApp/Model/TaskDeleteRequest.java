package pl.todo.ToDoListApp.Model;

import java.util.UUID;

public class TaskDeleteRequest {

    private long userId;
    private UUID externalId;

    public TaskDeleteRequest(long userId, UUID externalId) {
        this.userId = userId;
        this.externalId = externalId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }
}
