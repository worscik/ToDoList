package pl.todo.Model;

import java.time.Instant;

public class UpdateTaskRequest {

    private int id;
    private String name;
    private String description;
    private Instant startTaskTime;
    private Instant endTaskTime;

    public UpdateTaskRequest(int id, String name, String description, Instant startTaskTime, Instant endTaskTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTaskTime = startTaskTime;
        this.endTaskTime = endTaskTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
