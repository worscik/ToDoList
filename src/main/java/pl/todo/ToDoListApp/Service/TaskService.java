package pl.todo.ToDoListApp.Service;

import pl.todo.ToDoListApp.Model.BasicTaskResponse;
import pl.todo.ToDoListApp.Model.TaskListResponse;
import pl.todo.ToDoListApp.Model.TaskRequest;
import pl.todo.ToDoListApp.Model.UpdateTaskRequest;

import java.util.UUID;

public interface TaskService {

    BasicTaskResponse getTask(UUID externalId, long userId);

    TaskListResponse getTasks(long userId, TaskListResponse response);

    BasicTaskResponse insertTask(TaskRequest taskRequest);

    boolean removeTask(UUID externalId, long userId);

    BasicTaskResponse updateTask(UUID externalId, UpdateTaskRequest updateTaskRequest);

    int countCompleted(long userId);


}
