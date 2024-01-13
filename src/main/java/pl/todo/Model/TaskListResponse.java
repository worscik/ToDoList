package pl.todo.Model;

import java.util.List;

public class TaskListResponse {

    private List<Task> tasks;
    private String errorMsg;

    public TaskListResponse(List<Task> tasks, String errorMsg) {
        this.tasks = tasks;
        this.errorMsg = errorMsg;
    }

    public TaskListResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskListResponse() {
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
