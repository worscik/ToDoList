package pl.todo.User.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.todo.ToDoListApp.Controller.WebController;
import pl.todo.User.Model.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    RestTemplate restTemplate = new RestTemplate();
    public static final String url = "http://localhost:8081/getUserById?id=";
    private static Logger logger = LogManager.getLogger(WebController.class);


    @Override
    public User resolveUser(User user) {
        ResponseEntity<User> response = restTemplate.getForEntity(url + user.getId(), User.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("Response code while download user from service: {}", response.getStatusCode());
            return null;
        }
        User findedUser = new User.Builder()
                .withId(response.getBody().getId())
                .withLogin(response.getBody().getLogin())
                .build();
        return findedUser;
    }

    public User buildUser(long id, String name){
        User user = new User.Builder()
            .withId(id)
                .withLogin(name)
                .build();
        return user;
    }



}
