package pl.todo.Model;

public class TaskResponse {

    private Task task;
    private String errorMsg;

    public TaskResponse() {
    }

    public TaskResponse(Task task) {
        this.task = task;

    }

    public TaskResponse(Task task, String errorMsg) {
        this.task = task;
        this.errorMsg = errorMsg;
    }

    public TaskResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
