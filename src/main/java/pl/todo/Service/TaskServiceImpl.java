package pl.todo.Service;

import org.springframework.stereotype.Service;
import pl.todo.Model.Task;
import pl.todo.Model.TaskRequest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final ToDoRepository toDoRepository;

    public TaskServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public Task getTask(int id) {
        Task task = toDoRepository.getTask(id);
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return toDoRepository.getTasks();
    }

    @Override
    public Task addTask(TaskRequest taskRequest) {
        Task task = new Task.Builder()
                .uuid(UUID.randomUUID())
                .name("Task Test")
                .description("Test task description")
                .startTaskTime(taskRequest.getStartTaskTime())
                .endTaskTime(taskRequest.getEndTaskTime())
                .createdOn(Instant.EPOCH)
                .build();
        toDoRepository.addTask(task);
        System.out.println("Added product: " + task);
        return task;
    }

    @Override
    public Task removeTask(int id) {
        return null;
    }

    @Override
    public Task updateTask(TaskRequest taskRequest) {
        return null;
    }

    @Override
    public Task insert(TaskRequest taskRequest) {
        return null;
    }
}
