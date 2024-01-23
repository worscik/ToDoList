package pl.todo.ToDoListApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.todo.ToDoListApp.Model.*;
import pl.todo.ToDoListApp.Repository.TaskDao;

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
    public TaskResponse getTask(UUID externalId, long userId) {
        Task task = taskDao.getTask(externalId, userId);
        if (Objects.isNull(task)) {
            log.info("Task with id {} not found for user: {}", externalId, userId);
            return new TaskResponse(NOT_FOUND_TASK);
        }
        return new TaskResponse(task);
    }

    @Override
    public TaskListResponse getTasks(long userId, TaskListResponse response) {
        List<Task> listTasks = taskDao.getTasks(userId);
        if (listTasks.isEmpty()) {
            log.info("Tasks not found for user {}",userId );
            return new TaskListResponse(Collections.emptyList(), NOT_FOUND_TASK);
        }
        return new TaskListResponse(listTasks);
    }

    @Override
    public TaskResponse insertTask(TaskRequest taskRequest, TaskResponse taskResponse) {
        Task task = buildTask(taskRequest);
        Optional<Task> isExist = Optional.ofNullable(taskDao.findTask(task.getExternalId(),taskRequest.getUserId()));
        if (isExist.isPresent()) {
            log.info("Task {0} not found", isExist.get().getId());
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
    public TaskResponse updateTask(UUID externalId, UpdateTaskRequest request, TaskResponse taskResponse) {
        try {
            return updateTaskInternal(externalId,request);
        } catch (Exception e) {
            log.error("An error occurred while updating the task", e);
            return new TaskResponse(taskResponse.getTask(), "Error occurred during task update.");
        }
    }

    @Override
    public int countCompleted(long userId) {
        return taskDao.countCompletedTasks(userId);
    }

    @Override
    public boolean removeTask(UUID externalId, long userId) {
        try {
            int result = taskDao.deleteTask(externalId, userId);
            return result > 0;
        } catch (Exception e) {
            log.error("An error occurred while removing the task", e);
            return false;
        }
    }

    private TaskResponse updateTaskInternal(UUID externalId,UpdateTaskRequest request) {
        Optional<Task> optionalTask = Optional.ofNullable(taskDao.findTask(externalId, request.getUserId()));
        try {
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                task.setName(request.getName());
                task.setVersion(task.getVersion() + NEW_VERSION);
                task.setModifyOn(Instant.now());
                task.setDescription(request.getDescription());
                task.setStartTaskTime(resolveTime(request.getStartTaskTime(), task.getStartTaskTime()));
                task.setEndTaskTime(resolveTime(request.getEndTaskTime(), task.getEndTaskTime()));
                task.setStatusTask(request.getStatusTask());

                taskDao.updateTask(task);
                log.info("Updated task: {}", task);
                return new TaskResponse(task);
            } else {
                return new TaskResponse(null, "Task not found.");
            }
        } catch (Exception e) {
            log.error("Error during update task: {0}, errorCode: {1}", optionalTask.get().getId(), e);
            return new TaskResponse(null, "Error during update task");
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
                .version(0)
                .modifyOn(null)
                .userId(1)
                .build();
        return task;
    }


}


