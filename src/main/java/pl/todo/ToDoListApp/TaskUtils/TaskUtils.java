package pl.todo.ToDoListApp.TaskUtils;

import pl.todo.ToDoListApp.Model.TaskRequest;
import pl.todo.ToDoListApp.Model.UpdateTaskRequest;

public class TaskUtils {

    public static boolean validate(UpdateTaskRequest updateTaskRequest) {
        if (updateTaskRequest.getName().equals("") || updateTaskRequest.getName().isBlank() ||
                updateTaskRequest.getName().length() > 128) {
            return false;
        }
        if (updateTaskRequest.getDescription().equals("") || updateTaskRequest.getDescription().isEmpty() ||
                updateTaskRequest.getDescription().length() > 1028) {
            return false;
        }
        return true;
    }

    public static boolean validate(TaskRequest taskRequest) {
        if (taskRequest.getName().equals("") || taskRequest.getName().isBlank() ||
                taskRequest.getName().length() > 128) {
            return false;
        }
        if (taskRequest.getDescription().equals("") || taskRequest.getDescription().isEmpty() ||
                taskRequest.getDescription().length() > 1028) {
            return false;
        }
        return true;
    }

}
