package pl.todo.ToDoListApp.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicTaskResponse {

    @JsonProperty("success")
    boolean success;
    @JsonProperty("message")
    private List<String> message;
    @JsonProperty("task")
    private TaskDto taskDto;

    public BasicTaskResponse() {
    }

    public BasicTaskResponse(boolean success, List<String> message, TaskDto taskDto) {
        this.success = success;
        this.message = message;
        this.taskDto = taskDto;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }
}
