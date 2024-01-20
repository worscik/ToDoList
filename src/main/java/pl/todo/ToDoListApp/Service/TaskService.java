package pl.todo.ToDoListApp.Service;

import pl.todo.ToDoListApp.Model.*;

import java.util.UUID;

public interface TaskService {

    TaskResponse getTask(UUID externalId, long userId);

    TaskListResponse getTasks(long userId, TaskListResponse response);

    TaskResponse insertTask(TaskRequest taskRequest, TaskResponse taskResponse);

    boolean removeTask(UUID externalId, long userId);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, TaskResponse taskResponse);

    boolean validate(TaskRequest taskRequest);

    boolean validate(UpdateTaskRequest updateTaskRequest);


}
