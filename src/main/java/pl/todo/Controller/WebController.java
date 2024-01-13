package pl.todo.Controller;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.todo.Model.TaskListResponse;
import pl.todo.Model.TaskRequest;
import pl.todo.Model.TaskResponse;
import pl.todo.Model.UpdateTaskRequest;
import pl.todo.Service.TaskServiceImpl;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class WebController {

    private final TaskServiceImpl taskService;

    public WebController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public String homePage() {
        return "Home page";
    }

    @GetMapping("/getTaskById")
    public ResponseEntity<TaskResponse> getTaskById(@RequestParam int id) {
        TaskResponse task = taskService.getTask(id);

        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<TaskListResponse> getTasks() {
        TaskListResponse response = new TaskListResponse();
        TaskListResponse result = taskService.getTasks(response);

        if (result.getTasks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(taskRequest)) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.insertTask(taskRequest, taskResponse));
    }

    @PostMapping("/update")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        if (!taskService.validate(updateTaskRequest) ||
                StringUtils.isBlank(String.valueOf(updateTaskRequest.getExternalId()))) {
            return ResponseEntity.badRequest().body(taskResponse);
        }
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest, taskResponse));
    }
}
