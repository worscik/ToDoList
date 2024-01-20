package pl.todo.ToDoListApp.Model;

import java.util.UUID;

public class GetTaskRequest {

    private UUID externalId;
    private long userId;

    public GetTaskRequest(UUID externalId, long userId) {
        this.externalId = externalId;
        this.userId = userId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
