package pl.todo.ToDoListApp.Controller;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.todo.ToDoListApp.Model.*;
import pl.todo.ToDoListApp.Service.TaskServiceImpl;
import pl.todo.User.Model.User;
import pl.todo.User.Model.UserIdRequest;
import pl.todo.User.Service.UserServiceImpl;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WebController {

    private final TaskServiceImpl taskService;
    private final UserServiceImpl userService;
    private static Logger logger = LogManager.getLogger(WebController.class);


    public WebController(TaskServiceImpl taskService, UserServiceImpl userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        User user = userService.buildUser(taskRequest.getUserId(), taskRequest.getName());
        Optional<User> result = Optional.ofNullable(userService.resolveUser(user));
        if (!result.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", user.getId());
            return ResponseEntity.notFound().build();
        }
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(taskRequest)) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.insertTask(taskRequest, taskResponse));
    }

    @PostMapping("/updateTask")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        User user = new User.Builder().withId(updateTaskRequest.getUserId()).build();
        Optional<User> result = Optional.ofNullable(userService.resolveUser(user));
        if (!result.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", user.getId());
            return ResponseEntity.notFound().build();
        }
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(updateTaskRequest) ||
                StringUtils.isBlank(String.valueOf(updateTaskRequest.getExternalId()))) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest, taskResponse));
    }

    @PostMapping("/getTaskById")
    public ResponseEntity<TaskResponse> getTaskById(@RequestBody GetTaskRequest getTaskRequest) {
        User user = new User.Builder().withId(getTaskRequest.getUserId()).build();
        Optional<User> result = Optional.ofNullable(userService.resolveUser(user));
        if (!result.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", user.getId());
            return ResponseEntity.notFound().build();
        }

        TaskResponse task = taskService.getTask(getTaskRequest.getExternalId(), getTaskRequest.getUserId());

        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/getTasks")
    public ResponseEntity<TaskListResponse> getTasks(@RequestBody UserIdRequest userIdRequest) {
        User user = new User.Builder().withId(userIdRequest.getUserId()).build();
        Optional<User> userResult = Optional.ofNullable(userService.resolveUser(user));
        if (!userResult.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", user.getId());
            return ResponseEntity.notFound().build();
        }

        TaskListResponse response = new TaskListResponse();
        TaskListResponse result = taskService.getTasks(userIdRequest.getUserId(), response);

        if (result.getTasks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteTaskById") // TODO CLASS
    public ResponseEntity<Boolean> removeTaskById(@RequestBody int id, long userId) {
        User user = new User.Builder().withId(userId).build();
        Optional<User> userResult = Optional.ofNullable(userService.resolveUser(user));
        if (!userResult.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", user.getId());
            return ResponseEntity.notFound().build();
        }
        return null;
    }
}
