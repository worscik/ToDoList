package pl.todo.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "application_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private UUID externalId;
    private String email;
    private String password;
    private UserRole userRole;

    public User() {
    }

    private User(UUID externalId, String email, String password, UserRole userRole) {
        this.externalId = externalId;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public static User createUser(UUID externalId, String email, String password, UserRole userRole) {
        return new User(externalId, email, password, userRole);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }
}
