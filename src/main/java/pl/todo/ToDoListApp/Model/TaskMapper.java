package pl.todo.ToDoListApp.Model;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto map(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setExternalId(task.getExternalId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStartTaskTime(task.getStartTaskTime());
        taskDto.setEndTaskTime(task.getEndTaskTime());
        taskDto.setStatusTask(task.getStatusTask());
        return taskDto;
    }

}
