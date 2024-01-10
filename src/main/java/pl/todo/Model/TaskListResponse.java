package pl.todo.Model;

import java.util.List;

public class TaskListResponse {

    private List<Task> task;
    private String errorMsg;

    public TaskListResponse(List<Task> task, String errorMsg) {
        this.task = task;
        this.errorMsg = errorMsg;
    }

    public TaskListResponse(List<Task> task) {
        this.task = task;
    }

    public TaskListResponse() {
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
