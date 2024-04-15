package pl.todo.ToDoListApp.Controller;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.todo.ToDoListApp.Model.BasicTaskResponse;
import pl.todo.ToDoListApp.Model.TaskListResponse;
import pl.todo.ToDoListApp.Model.TaskRequest;
import pl.todo.ToDoListApp.Model.UpdateTaskRequest;
import pl.todo.ToDoListApp.Service.TaskServiceImpl;

import java.util.Objects;
import java.util.UUID;

import static pl.todo.ToDoListApp.TaskUtils.TaskUtils.validate;

@RestController
@RequestMapping("/task")
@CrossOrigin(value = "*")
public class WebController {

    private final TaskServiceImpl taskService;
    private static Logger logger = LogManager.getLogger(WebController.class);

    public WebController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity<BasicTaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        BasicTaskResponse result = new BasicTaskResponse();
        if (!validate(taskRequest)) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(taskService.insertTask(taskRequest));
    }

    @PostMapping("/{externalId}")
    public ResponseEntity<BasicTaskResponse> updateTask(@PathVariable UUID externalId,
                                                        @RequestBody UpdateTaskRequest updateTaskRequest) {
        BasicTaskResponse errorTaskResponse = new BasicTaskResponse();
        if (!validate(updateTaskRequest) ||
                StringUtils.isBlank(String.valueOf(externalId))) {
            return ResponseEntity.badRequest().body(errorTaskResponse);
        }
        return ResponseEntity.ok(taskService.updateTask(externalId, updateTaskRequest));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<BasicTaskResponse> updatePartOfTask(@PathVariable UUID externalId,
                                                              @RequestBody UpdateTaskRequest updateTaskRequest) {

//        if (!validate(updateTaskRequest) ||
//                StringUtils.isBlank(String.valueOf(externalId))) {
//            return ResponseEntity.badRequest().body(result);
//        }
        return ResponseEntity.ok(taskService.updateTaskByElements(externalId, updateTaskRequest));
    }

    @GetMapping("/task/{externalId}/{userId}")
    public ResponseEntity<BasicTaskResponse> getTaskById(@PathVariable UUID externalId,
                                                         @PathVariable long userId) {
        BasicTaskResponse task = taskService.getTask(externalId, userId);

        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<TaskListResponse> getTasks(@PathVariable long userId) {

        TaskListResponse response = new TaskListResponse();
        TaskListResponse result = taskService.getTasks(userId, response);

        if (result.getTasks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{externalId}/{userId}")
    public ResponseEntity<Boolean> removeTaskById(@PathVariable UUID externalId,
                                                  @PathVariable long userId) {

        logger.info("Removed task for user {} and externalID: {}", userId, externalId);
        return ResponseEntity.ok(taskService.removeTask(externalId, userId));
    }

    @GetMapping("/countCompleted/{userId}")
    public long getCompletedTask(@PathVariable long userId) {
        return taskService.countCompleted(userId);
    }

}
