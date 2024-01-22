package pl.todo.ToDoListApp.Controller;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.todo.ToDoListApp.Model.*;
import pl.todo.ToDoListApp.Service.TaskServiceImpl;
import pl.todo.User.Model.UserDto;
import pl.todo.User.Service.UserServiceImpl;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task/api")
public class WebController {

    private final TaskServiceImpl taskService;
    private final UserServiceImpl userService;
    private static Logger logger = LogManager.getLogger(WebController.class);


    public WebController(TaskServiceImpl taskService, UserServiceImpl userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        UserDto userDto = userService.buildUser(taskRequest.getUserId(), taskRequest.getName());
        Optional<UserDto> result = Optional.ofNullable(userService.resolveUser(userDto));
        if (!result.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", userDto.getId());
            return ResponseEntity.notFound().build();
        }
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(taskRequest)) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.insertTask(taskRequest, taskResponse));
    }

    @PostMapping("/update/{externalId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID externalId,
                                                   @RequestBody UpdateTaskRequest updateTaskRequest) {
        UserDto userDto = new UserDto.Builder().withId(updateTaskRequest.getUserId()).build();
        Optional<UserDto> result = Optional.ofNullable(userService.resolveUser(userDto));
        if (!result.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", userDto.getId());
            return ResponseEntity.notFound().build();
        }
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(updateTaskRequest) ||
                StringUtils.isBlank(String.valueOf(externalId))) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.updateTask(externalId,updateTaskRequest, taskResponse));
    }

    @GetMapping("/get/{externalId}/{userId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID externalId,
                                                    @PathVariable long userId) {
        UserDto userDto = new UserDto.Builder().withId(userId).build();
        Optional<UserDto> result = Optional.ofNullable(userService.resolveUser(userDto));
        if (!result.isPresent()) {
            logger.info("User not found in the database: {}", userDto.getId());
            return ResponseEntity.notFound().build();
        }

        TaskResponse task = taskService.getTask(externalId, userId);

        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/getTasks/{userId}")
    public ResponseEntity<TaskListResponse> getTasks(@PathVariable long userId) {
        UserDto userDto = new UserDto.Builder().withId(userId).build();
        Optional<UserDto> userResult = Optional.ofNullable(userService.resolveUser(userDto));
        if (!userResult.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", userDto.getId());
            return ResponseEntity.notFound().build();
        }

        TaskListResponse response = new TaskListResponse();
        TaskListResponse result = taskService.getTasks(userId, response);

        if (result.getTasks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{externalId}/{userId}")
    public ResponseEntity<Boolean> removeTaskById(@PathVariable UUID externalId,
                                                  @PathVariable long userId) {
        UserDto userDto = new UserDto.Builder().withId(userId).build();
        Optional<UserDto> userResult = Optional.ofNullable(userService.resolveUser(userDto));
        if (!userResult.isPresent()) {
            logger.info("UserService response: user with id {} not found in database", userDto.getId());
            return ResponseEntity.notFound().build();
        }
        logger.info("Removed task for user {} and externalID: {}", userId,externalId);
        return ResponseEntity.ok(taskService.removeTask(externalId,userId));
    }

    @GetMapping("/count/{userId}")
    public long getCompletedTask(){
        return 1;
    }

}
