package pl.todo.ToDoListApp.Service;

import pl.todo.ToDoListApp.Model.*;

import java.util.UUID;

public interface TaskService {

    TaskResponse getTask(UUID externalId, long userId);

    TaskListResponse getTasks(long userId, TaskListResponse response);

    TaskResponse insertTask(TaskRequest taskRequest);

    boolean removeTask(UUID externalId, long userId);

    TaskResponse updateTask(UUID externalId, UpdateTaskRequest updateTaskRequest, TaskResponse taskResponse);

    int countCompleted(long userId);


}
