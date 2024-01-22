package pl.todo.User.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.todo.ToDoListApp.Controller.WebController;
import pl.todo.User.Model.UserDto;

@Service
public class UserServiceImpl implements UserService {

    RestTemplate restTemplate = new RestTemplate();
    public static final String url = "http://localhost:8081/getUserById?id=";
    public static final String urlByName = "http://localhost:8081/getUserById?name=";
    private static Logger logger = LogManager.getLogger(WebController.class);


    public UserDto resolveUser(UserDto userDto) {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(url + userDto.getId(), UserDto.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("Response code while download user from service: {}", response.getStatusCode());
            return null;
        }
        UserDto findedUserDto = new UserDto.Builder()
                .withId(response.getBody().getId())
                .withLogin(response.getBody().getLogin())
                .build();
        return findedUserDto;
    }

    public UserDto findUser(String username) {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(url + username, UserDto.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("Response code while download user from service: {}", response.getStatusCode());
            return null;
        }
        UserDto findedUserDto = new UserDto.Builder()
                .withId(response.getBody().getId())
                .withLogin(response.getBody().getLogin())
                .build();
        return findedUserDto;
    }

    public UserDto buildUser(long id, String name){
        UserDto userDto = new UserDto.Builder()
            .withId(id)
                .withLogin(name)
                .build();
        return userDto;
    }



}
