package pl.todo.ToDoListApp.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import pl.todo.ToDoListApp.Model.Task;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class TaskDao {

    private final EntityManager entityManager;


    public TaskDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Task getTask(UUID externalId, long userId) {
        try {
            String sqlQuery = "SELECT * FROM TASK WHERE external_id = :externalId AND user_id = :userId";
            Query nativeQuery = entityManager.createNativeQuery(sqlQuery, Task.class);
            nativeQuery.setParameter("externalId", externalId);
            nativeQuery.setParameter("userId", userId);
            return (Task) nativeQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Task> getTasks(long userId) {
        try{
            return entityManager.createNativeQuery("SELECT * FROM task WHERE user_id = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        } catch (NoResultException e){
            return Collections.emptyList();
        }
    }

    @Transactional
    public void addTask(Task task) {
        entityManager.persist(task);
    }

    @Transactional
    public Task updateTask(Task task) {
        return entityManager.merge(task);
    }

    @Transactional
    public Task findTask(UUID externalId, long userId) {
        try {
            return (Task) entityManager.createNativeQuery("SELECT * FROM task WHERE external_id = :externalId " +
                            "AND user_id = :userId", Task.class)
                    .setParameter("externalId", externalId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

    @Transactional
    public int deleteTask(UUID externalId, long userId) {
        Query result = entityManager.createNativeQuery("DELETE FROM task WHERE external_id = :externalId and user_id = :userId")
                .setParameter("externalId", externalId)
                .setParameter("userId", userId);
        return result.executeUpdate();
    }
}
