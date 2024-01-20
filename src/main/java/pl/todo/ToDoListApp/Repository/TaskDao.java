package pl.todo.ToDoListApp.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import pl.todo.ToDoListApp.Model.Task;
import pl.todo.ToDoListApp.Model.TaskDto;

import java.util.List;
import java.util.UUID;

@Repository
public class TaskDao {

    private final EntityManager entityManager;
    private SessionFactory sessionFactory;



    public TaskDao(EntityManager entityManager, SessionFactory sessionFactory) {
        this.entityManager = entityManager;
        this.sessionFactory = sessionFactory;
    }

    public Task getTask(UUID externalId, long userId) {
        String sqlQuery = "SELECT * FROM TASK WHERE external_id = :externalId AND user_id = :userId";
        Session session = sessionFactory.openSession();
        NativeQuery<Task> nativeQuery = session.createNativeQuery(sqlQuery, Task.class);
        nativeQuery.setParameter("externalId", externalId);
        nativeQuery.setParameter("userId", userId);
        Task result = nativeQuery.uniqueResult();
        session.close();
        return result;

    }

    public List<Task> getTasks(long userId) {
        List<Task> result = sessionFactory.openSession().createNativeQuery("SELECT * FROM task WHERE user_id = :userId", Task.class)
                .setParameter("userId", userId)
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
    public Task findTask(UUID externalId, long userId){
        try{
            return (Task) entityManager.createNativeQuery("SELECT * FROM task WHERE external_id = :extId " +
                            "AND user_id = :userId", Task.class)
                    .setParameter("extId", externalId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e){}
        return null;
    }

    @Transactional
    public boolean deleteTask(UUID externalId, long userId){
        String sqlQuery = ("DELETE * FROM TASK WHERE external_id = :extId and user_id = :userId");
        Session session = sessionFactory.openSession();
        NativeQuery<Boolean> nativeQuery = session.createNativeQuery(sqlQuery, Boolean.class);
        nativeQuery.setParameter("externalId", externalId);
        nativeQuery.setParameter("userId", userId);
        boolean result = nativeQuery.uniqueResult();
        return result;
    }
}
