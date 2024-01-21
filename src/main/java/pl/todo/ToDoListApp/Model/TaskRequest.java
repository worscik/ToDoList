package pl.todo.ToDoListApp.Model;

import java.time.Instant;

public class TaskRequest {
    private String name;
    private String description;
    private Instant startTaskTime;
    private Instant endTaskTime;
    private long userId;
    private StatusTask statusTask;

    public TaskRequest(String name, String description, Instant startTaskTime, Instant endTaskTime, StatusTask statusTask, long userId) {
        this.name = name;
        this.description = description;
        this.startTaskTime = startTaskTime;
        this.endTaskTime = endTaskTime;
        this.statusTask = statusTask;
        this.userId = userId;

    }


    public static TaskRequest create(String name,
                                     String description,
                                     Instant startTaskTime,
                                     Instant endTaskTime,
                                     StatusTask statusTask,
                                     long userId) {
        return new TaskRequest(name, description, startTaskTime, endTaskTime, statusTask,userId);
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public StatusTask getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(StatusTask statusTask) {
        this.statusTask = statusTask;
    }
}
