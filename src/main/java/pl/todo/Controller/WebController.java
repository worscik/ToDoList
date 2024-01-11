package pl.todo.Controller;

import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.todo.Model.*;
import pl.todo.Service.TaskServiceImpl;

import java.net.http.HttpRequest;
import java.util.List;
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
    public Task getTaskById(@RequestParam int id) {
        return taskService.getTask(id);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<TaskListResponse> getTasks() {
        TaskListResponse response = new TaskListResponse();
        return new ResponseEntity<>(taskService.getTasks(response),HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        if(!taskService.validate(taskRequest)){
            return new ResponseEntity("Request is not correct", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(taskService.insertTask(taskRequest, taskResponse), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        if(!taskService.validate(updateTaskRequest)){
            if(updateTaskRequest.getExternalId().equals("")){
                return new ResponseEntity("ExternalId can not be empty.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Request is not correct", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(taskService.updateTask(updateTaskRequest, taskResponse), HttpStatus.OK);
    }
}
