package pl.todo.ToDoListApp.Model;

import java.time.Instant;
import java.util.UUID;

public class TaskDto {

    private UUID externalId;
    private String name;
    private String description;
    private Instant startTaskTime;
    private Instant endTaskTime;
    private Instant createdOn;

    public TaskDto(UUID externalId, String name, String description, Instant startTaskTime, Instant endTaskTime, Instant createdOn) {
        this.externalId = externalId;
        this.name = name;
        this.description = description;
        this.startTaskTime = startTaskTime;
        this.endTaskTime = endTaskTime;
        this.createdOn = createdOn;
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

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
}

