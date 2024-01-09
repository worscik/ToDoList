package pl.todo.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.todo.Model.Task;

import java.util.List;
import java.util.UUID;

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
    public Task findTask(UUID externalId){
        try{
            return (Task) entityManager.createNativeQuery("SELECT * FROM TASK WHERE EXTERNAL_ID = :extId", Task.class)
                    .setParameter("extId",externalId)
                    .getSingleResult();
        } catch (NoResultException e){}
        return null;
    }
}
