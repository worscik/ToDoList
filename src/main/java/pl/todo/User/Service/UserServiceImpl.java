package pl.todo.User.Service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.todo.User.Model.User;

import java.net.ConnectException;

@Service
public class UserServiceImpl implements UserService {

    RestTemplate restTemplate = new RestTemplate();
    public static final String url = "http://localhost:8081/getUserById?id=";
    public static final HttpMethod method = HttpMethod.GET;



    @Override
    public User resolveUser(User user) {
        ResponseEntity<User> response = restTemplate.getForEntity(url+user.getId(), User.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            return null;
        }
        return response.getBody();
    }
}
