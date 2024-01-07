package pl.todo.Service;

import pl.todo.Model.Task;
import pl.todo.Model.TaskRequest;

import java.util.List;

public interface TaskService {

    Task getTask(int id);

    List<Task> getTasks();

    Task addTask(TaskRequest taskRequest);

    Task removeTask(int id);

    Task updateTask(TaskRequest taskRequest);

    Task insert(TaskRequest taskRequest);

}
