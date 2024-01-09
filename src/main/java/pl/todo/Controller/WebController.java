package pl.todo.Controller;

import org.springframework.web.bind.annotation.*;
import pl.todo.Model.Task;
import pl.todo.Model.TaskRequest;
import pl.todo.Model.TaskResponse;
import pl.todo.Model.UpdateTaskRequest;
import pl.todo.Service.TaskServiceImpl;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/task")
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
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/insert")
    public Task addTask(@RequestBody TaskRequest taskRequest) {
        if(!taskService.validate(taskRequest)){
            return null;
        }
        return taskService.insertTask(taskRequest);
    }

    @PostMapping("/update")
    public TaskResponse updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        if(!taskService.validate(updateTaskRequest)){
            return null;
        }
        return taskService.updateTask(updateTaskRequest, taskResponse);
    }
}
