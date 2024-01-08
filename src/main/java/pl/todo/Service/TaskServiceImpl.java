package pl.todo.Service;

import org.springframework.stereotype.Service;
import pl.todo.Model.Task;
import pl.todo.Model.TaskRequest;
import pl.todo.Model.UpdateTaskRequest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    public final static int NEW_VERSION = 1;

    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task getTask(int id) {
        Task task = taskDao.getTask(id);
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return taskDao.getTasks();
    }

    @Override
    public Task insertTask(TaskRequest taskRequest) {
        Task task = new Task.Builder()
                .uuid(UUID.randomUUID())
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .startTaskTime(taskRequest.getStartTaskTime())
                .endTaskTime(taskRequest.getEndTaskTime())
                .createdOn(Instant.now())
                .setVersion(0)
                .setModifyOn(null)
                .build();
        taskDao.addTask(task);
        System.out.println("Added task: " + task);
        return task;
    }

    @Override
    public Task removeTask(int id) {
        return null;
    }

    @Override
    public Task updateTask(UpdateTaskRequest updateTaskRequest) {
        Task task = taskDao.findTask(updateTaskRequest.getId());
        task.setName(updateTaskRequest.getName());
        task.setVersion(task.getVersion() + NEW_VERSION);
        task.setModifyOn(Instant.now());
        task.setDescription(updateTaskRequest.getDescription());
        taskDao.updateTask(task);
        System.out.println("Updated task: " + task);
        return task;
    }

    @Override
    public boolean validate(TaskRequest taskRequest) {
        return validateNamespace(taskRequest.getName());
    }

    @Override
    public boolean validate(UpdateTaskRequest updateTaskRequest) {
        return false;
    }


    private boolean validateNamespace(String name) {
        if (name.equals("") || name.isEmpty()) {
            return false;
        }
        if (name.length() > 255) {
            return false;
        }
        return true;
    }
}
