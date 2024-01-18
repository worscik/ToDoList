package pl.todo.ToDoListApp.Controller;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.todo.ToDoListApp.Model.TaskListResponse;
import pl.todo.ToDoListApp.Model.TaskRequest;
import pl.todo.ToDoListApp.Model.TaskResponse;
import pl.todo.ToDoListApp.Model.UpdateTaskRequest;
import pl.todo.ToDoListApp.Service.TaskServiceImpl;
import pl.todo.User.Model.User;
import pl.todo.User.Service.UserServiceImpl;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WebController {

    private final TaskServiceImpl taskService;
    private final UserServiceImpl userService;

    public WebController(TaskServiceImpl taskService, UserServiceImpl userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @RequestMapping("/")
    public String homePage() {
        return "Home page";
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest,
                                                Principal principal) {
        User user = new User.Builder()
                .withId(taskRequest.getUserId())
                .withLogin(principal.getName())
                .build();
        Optional<User> result = Optional.ofNullable(userService.resolveUser(user));
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(taskRequest)) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.insertTask(taskRequest, taskResponse));
    }

    @PostMapping("/updateTask")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest,
                                                   Principal principal) {
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(updateTaskRequest) ||
                StringUtils.isBlank(String.valueOf(updateTaskRequest.getExternalId()))) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest, taskResponse));
    }

    @GetMapping("/getTaskById")
    public ResponseEntity<TaskResponse> getTaskById(@RequestParam int id, Principal principal) {
        TaskResponse task = taskService.getTask(id);

        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<TaskListResponse> getTasks(Principal principal) {
        TaskListResponse response = new TaskListResponse();
        TaskListResponse result = taskService.getTasks(response);

        if (result.getTasks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteTaskById")
    public ResponseEntity<Boolean> removeTaskById(@RequestBody int id, Principal principal) {
        return null;
    }
}
