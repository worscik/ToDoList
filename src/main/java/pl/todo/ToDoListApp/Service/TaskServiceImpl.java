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

    private static final String NOT_FOUND_TASK = "The task was not found.";
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    public final static int NEW_VERSION = 1;
    private final TaskDao taskDao;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskDao taskDao, TaskMapper taskMapper) {
        this.taskDao = taskDao;
        this.taskMapper = taskMapper;
    }

    @Override
    public BasicTaskResponse getTask(UUID externalId, long userId) {
        Optional<Task> task = taskDao.getTask(externalId, userId);
        if (Objects.isNull(task)) {
            log.info("Task with id {} not found for user: {}", externalId, userId);
            return new BasicTaskResponse(false, List.of(NOT_FOUND_TASK), null);
        }
        return new BasicTaskResponse(true, List.of(""), taskMapper.map(task.get()));
    }

    @Override
    public TaskListResponse getTasks(long userId, TaskListResponse response) {
        List<Task> listTasks = taskDao.getTasks(userId);
        if (listTasks.isEmpty()) {
            log.info("Tasks not found for user {}", userId);
            return new TaskListResponse(Collections.emptyList(), NOT_FOUND_TASK);
        }
        return new TaskListResponse(listTasks);
    }

    @Override
    public BasicTaskResponse insertTask(TaskRequest taskRequest) {
        Task task = buildTask(taskRequest);
        try {
            taskDao.addTask(task);
            log.info("Added task: " + task);
            return new BasicTaskResponse(true, null, taskMapper.map(task));
        } catch (Exception e) {
            log.error("An error occurred when try to add task" + e);
            return new BasicTaskResponse(false, List.of("Error during insert the task"), null);
        }
    }

    @Override
    public BasicTaskResponse updateTask(UUID externalId, UpdateTaskRequest request) {
        try {
            return updateTaskInternal(externalId, request);
        } catch (Exception e) {
            log.error("An error occurred while updating the task", e);
            return new BasicTaskResponse(false, List.of("Error during update the task"), null);
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

    private BasicTaskResponse updateTaskInternal(UUID externalId, UpdateTaskRequest request) {
        Optional<Task> optionalTask = Optional.ofNullable(taskDao.findTask(externalId, request.getUserId()));
        try {
            if (optionalTask.isPresent()) {
                Task updatedTask = taskDao.updateTask(buildTaskToUpdate(optionalTask.get(), request));
                log.info("Updated task: {}", updatedTask);
                return new BasicTaskResponse(true, null, taskMapper.map(updatedTask));
            } else {
                return new BasicTaskResponse(false, List.of("Task not found."), null);
            }
        } catch (Exception e) {
            log.error("Error during update task: {}. ", optionalTask, e);
            return new BasicTaskResponse(false, List.of("Error during update the task"), null);
        }
    }

    public BasicTaskResponse updateTaskByElements(UUID externalId, UpdateTaskRequest request) {
        Optional<Task> optionalTask = Optional.ofNullable(taskDao.findTask(externalId, request.getUserId()));
        try {
            if (optionalTask.isPresent()) {
                Task task = setRecordsToChange(request, optionalTask.get());
                taskDao.addTask(task);
                log.info("Updated task: {}", task);
                return new BasicTaskResponse(true, null, taskMapper.map(task));
            } else {
                return new BasicTaskResponse(false, List.of("Task not found"), null);
            }
        } catch (Exception e) {
            log.error("Error during update task: {} .", optionalTask, e);
            return new BasicTaskResponse(false, List.of("Error during update Task by ID: " +
                    externalId), null);
        }
    }

    private Instant resolveTime(Instant oldTime, Instant newTime) {
        if (oldTime != newTime) {
            return newTime;
        }
        return oldTime;
    }

    private Task buildTask(TaskRequest taskRequest) {
        return new Task.Builder()
                .uuid(UUID.randomUUID())
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .startTaskTime(Instant.now())
                .endTaskTime(taskRequest.getEndTaskTime())
                .createdOn(Instant.now())
                .version(0)
                .status(taskRequest.getStatusTask())
                .modifyOn(null)
                .userId(1)
                .build();
    }

    private Task buildTaskToUpdate(Task task, UpdateTaskRequest request) {
        task.setName(request.getName());
        task.setVersion(task.getVersion() + NEW_VERSION);
        task.setModifyOn(Instant.now());
        task.setDescription(request.getDescription());
        task.setStartTaskTime(resolveTime(request.getStartTaskTime(), task.getStartTaskTime()));
        task.setEndTaskTime(resolveTime(request.getEndTaskTime(), task.getEndTaskTime()));
        task.setStatusTask(request.getStatusTask());
        return task;
    }

    private Task setRecordsToChange(UpdateTaskRequest request, Task task) {
        if (request.getName() != null) {
            task.setName(request.getName());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getStartTaskTime() != null) {
            task.setStartTaskTime(request.getStartTaskTime());
        }
        if (request.getEndTaskTime() != null) {
            task.setEndTaskTime(request.getEndTaskTime());
        }
        if (request.getStatusTask() != null) {
            task.setStatusTask(request.getStatusTask());
        }
        task.setVersion(task.getVersion() + NEW_VERSION);
        task.setModifyOn(Instant.now());
        return task;
    }

}


