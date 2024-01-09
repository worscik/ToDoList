package pl.todo.Service;

import pl.todo.Model.Task;
import pl.todo.Model.TaskRequest;
import pl.todo.Model.TaskResponse;
import pl.todo.Model.UpdateTaskRequest;

import java.util.List;

public interface TaskService {

    Task getTask(int id);

    List<Task> getTasks();

    Task insertTask(TaskRequest taskRequest);

    Task removeTask(int id);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, TaskResponse taskResponse);

    boolean validate(TaskRequest taskRequest);

    boolean validate(UpdateTaskRequest updateTaskRequest);


}
