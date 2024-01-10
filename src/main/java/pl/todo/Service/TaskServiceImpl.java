package pl.todo.Service;

import org.springframework.stereotype.Service;
import pl.todo.Model.*;

import java.time.Instant;
import java.util.*;

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
    public TaskListResponse getTasks(TaskListResponse response) {
        List<Task> listTasks = taskDao.getTasks();
        if (listTasks.isEmpty()) {
            return new TaskListResponse(Collections.emptyList(), "Not found tasks");
        }
        return new TaskListResponse(listTasks);
    }

    @Override
    public TaskResponse insertTask(TaskRequest taskRequest, TaskResponse taskResponse) {
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
        try {
            taskDao.addTask(task);
            System.out.println("Added task: " + task);
            return new TaskResponse(task);
        } catch (Exception e) {
            System.out.println(e);
            return new TaskResponse(task, "Error.");
        }
    }

    @Override
    public Task removeTask(int id) {
        return null;
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest request, TaskResponse taskResponse) {
        Task task = taskDao.findTask(request.getExternalId());
        if (Objects.isNull(task)) {
            return new TaskResponse(task, "Not found");
        }
        task.setName(request.getName());
        task.setVersion(task.getVersion() + NEW_VERSION);
        task.setModifyOn(Instant.now());
        task.setDescription(request.getDescription());
        task.setStartTaskTime(resolveTime(request.getStartTaskTime(), task.getStartTaskTime()));
        task.setEndTaskTime(resolveTime(request.getEndTaskTime(), task.getEndTaskTime()));
        try {
            taskDao.updateTask(task);
            System.out.println("Updated task: " + task);
            return new TaskResponse(task);
        } catch (Exception e) {
            System.out.println(e);
            return new TaskResponse(task, "Error.");
        }
    }

    @Override
    public boolean validate(TaskRequest taskRequest) {
        return validateNamespace(taskRequest.getName());
    }

    @Override
    public boolean validate(UpdateTaskRequest updateTaskRequest) {
        return validateNamespace(updateTaskRequest.getName());
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

    private Instant resolveTime(Instant oldTime, Instant newTime) {
        if (oldTime != newTime) {
            return newTime;
        }
        return oldTime;
    }
}
