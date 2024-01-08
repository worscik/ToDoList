package pl.todo.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.todo.Model.Task;

import java.util.List;

@Repository
public class TaskDao {

    private final EntityManager entityManager;

    public TaskDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Task getTask(int id) {
        Query result = entityManager.createNativeQuery("SELECT * FROM TASK WHERE ID = :id")
                .setParameter("id",id);
        return (Task) result;
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
        return entityManager.merge(task);
    }

    @Transactional
    public Task findTask(int id){
        return entityManager.find(Task.class, id);
    }
}
