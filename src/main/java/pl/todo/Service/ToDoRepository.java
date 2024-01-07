package pl.todo.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.todo.Model.Task;

import java.util.List;

@Repository
public class ToDoRepository {

    private final EntityManager entityManager;

    public ToDoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Task getTask(int id) {
        return entityManager.find(Task.class, id);
    }

    public List<Task> getTasks() {
        List<Task> result = entityManager.createNativeQuery("SELECT * FROM TASK")
                .getResultList();
        return result;
    }

    @Transactional
    public void addTask(Task task) {
        entityManager.persist(task);
    }

    @Transactional
    public Task updateTask(Task task){
        return null;
    }
}
