package pl.todo.ToDoListApp.Service;

import pl.todo.ToDoListApp.Model.*;

public interface TaskService {

    TaskResponse getTask(int id);

    TaskListResponse getTasks(TaskListResponse response);

    TaskResponse insertTask(TaskRequest taskRequest, TaskResponse taskResponse);

    Task removeTask(int id);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, TaskResponse taskResponse);

    boolean validate(TaskRequest taskRequest);

    boolean validate(UpdateTaskRequest updateTaskRequest);


}
