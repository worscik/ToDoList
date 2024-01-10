package pl.todo.Service;

import pl.todo.Model.*;

import java.util.List;

public interface TaskService {

    Task getTask(int id);

    TaskListResponse getTasks(TaskListResponse response);

    TaskResponse insertTask(TaskRequest taskRequest, TaskResponse taskResponse);

    Task removeTask(int id);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, TaskResponse taskResponse);

    boolean validate(TaskRequest taskRequest);

    boolean validate(UpdateTaskRequest updateTaskRequest);


}
