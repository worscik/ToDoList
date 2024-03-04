package pl.todo.User.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.todo.User.Model.User;

import java.util.Optional;

@Repository
public class UserDao {

    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public boolean findUserByCredentials(String email, String password) {
        try {
            User result = (User) entityManager.createNativeQuery("SELECT * FROM application_user WHERE " +
                            "email = :email AND password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Transactional
    public Optional<User> findUserByExternalId(String externalId) {
        try {
            User result = (User) entityManager.createNativeQuery(
                            "SELECT * FROM application_user WHERE externalId = :externalId", User.class)
                    .setParameter("externalId", externalId)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public User findUserByEmail(String email) {
        try {
            User result = (User) entityManager.createNativeQuery(
                            "SELECT * FROM application_user WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public User edit(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }
}
