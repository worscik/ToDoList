package pl.todo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.todo.Model.*;

import java.time.Instant;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    public final static int NEW_VERSION = 1;
    private final TaskDao taskDao;
    private static final String NOT_FOUND_TASK = "The task was not found.";
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public TaskResponse getTask(int id) {
        Task task = taskDao.getTask(id);
        if(Objects.isNull(task)){
            return new TaskResponse(NOT_FOUND_TASK);
        }
        return new TaskResponse(task);
    }

    @Override
    public TaskListResponse getTasks(TaskListResponse response) {
        List<Task> listTasks = taskDao.getTasks();
        if (listTasks.isEmpty()) {
            return new TaskListResponse(Collections.emptyList(), NOT_FOUND_TASK);
        }
        return new TaskListResponse(listTasks);
    }

    @Override
    public TaskResponse insertTask(TaskRequest taskRequest, TaskResponse taskResponse) {
        Task task = buildTask(taskRequest);
        Optional<Task> isExist = Optional.ofNullable(taskDao.findTask(task.getExternalId()));
        if (isExist.isPresent()) {
            return new TaskResponse(task, NOT_FOUND_TASK);
        }
        try {
            taskDao.addTask(task);
            log.info("Added task: " + task);
            return new TaskResponse(task);
        } catch (Exception e) {
            log.error("An error occurred when try to add task" + e);
            return new TaskResponse(task, "Error.");
        }
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest request, TaskResponse taskResponse) {
        try {
            return updateTaskInternal(request);
        } catch (Exception e) {
            log.error("An error occurred while updating the task", e);
            return new TaskResponse(taskResponse.getTask(), "Error occurred during task update.");
        }
    }

    @Override
    public Task removeTask(int id) {
        return null;
    }

    private TaskResponse updateTaskInternal(UpdateTaskRequest request) {

        Optional<Task> optionalTask = Optional.ofNullable(taskDao.findTask(request.getExternalId()));

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setName(request.getName());
            task.setVersion(task.getVersion() + NEW_VERSION);
            task.setModifyOn(Instant.now());
            task.setDescription(request.getDescription());
            task.setStartTaskTime(resolveTime(request.getStartTaskTime(), task.getStartTaskTime()));
            task.setEndTaskTime(resolveTime(request.getEndTaskTime(), task.getEndTaskTime()));

            taskDao.updateTask(task);
            log.info("Updated task: {}", task);
            return new TaskResponse(task);
        } else {
            return new TaskResponse(null, "Task not found.");
        }
    }

    @Override
    public boolean validate(TaskRequest taskRequest) {
        return validateRequest(taskRequest.getName(), taskRequest.getDescription());
    }

    @Override
    public boolean validate(UpdateTaskRequest updateTaskRequest) {
        return validateRequest(updateTaskRequest.getName(), updateTaskRequest.getDescription());
    }

    private Instant resolveTime(Instant oldTime, Instant newTime) {
        if (oldTime != newTime) {
            return newTime;
        }
        return oldTime;
    }

    public boolean validateRequest(String name, String description) {
        if (name.equals("") || name.isBlank() || name.length() > 128) {
            return false;
        }
        if (description.equals("") || description.isEmpty() || description.length() > 1028) {
            return false;
        }
        return true;
    }

    private Task buildTask(TaskRequest taskRequest) {
        Task task = new Task.Builder()
                .uuid(UUID.randomUUID())
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .startTaskTime(Instant.now())
                .endTaskTime(taskRequest.getEndTaskTime())
                .createdOn(Instant.now())
                .setVersion(0)
                .setModifyOn(null)
                .build();
        return task;
    }


}


