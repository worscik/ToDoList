package pl.todo.User.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import pl.todo.User.Model.User;
import pl.todo.User.Model.UserDto;

import java.util.Optional;

public class UserDao {

    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    boolean getUserCredentials(String email, String password) {
        try {
            User result = (User) entityManager.createNativeQuery("SELECT * FROM application_user WHERE " +
                    "email = :email AND password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return true;
        } catch (NoResultException e){
            return false;
        }
    }

    @Transactional
    Optional<User> findUserByExternalId(String externalId) {
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
    void addUser(User user) {
       try {
           entityManager.persist(user);
       } catch (Exception e){
           System.out.println(e);
       }
    }

    UserDto editUser(UserDto userDto) {
        return null;
    }

    boolean removeUser(UserDto userDto) {
        return true;
    }

    String newPassword(UserDto userDto) {
        return null;
    }
}
