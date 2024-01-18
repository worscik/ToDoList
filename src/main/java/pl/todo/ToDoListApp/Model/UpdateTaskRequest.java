package pl.todo.ToDoListApp.Model;

import java.time.Instant;
import java.util.UUID;

public class UpdateTaskRequest {

    private UUID externalId;
    private String name;
    private String description;
    private Instant startTaskTime;
    private Instant endTaskTime;

    public UpdateTaskRequest(UUID externalId, String name, String description, Instant startTaskTime, Instant endTaskTime) {
        this.externalId = externalId;
        this.name = name;
        this.description = description;
        this.startTaskTime = startTaskTime;
        this.endTaskTime = endTaskTime;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartTaskTime() {
        return startTaskTime;
    }

    public void setStartTaskTime(Instant startTaskTime) {
        this.startTaskTime = startTaskTime;
    }

    public Instant getEndTaskTime() {
        return endTaskTime;
    }

    public void setEndTaskTime(Instant endTaskTime) {
        this.endTaskTime = endTaskTime;
    }
}
