package pl.todo.User.Controller;

import org.springframework.web.bind.annotation.RestController;
import pl.todo.User.UserService.UserService;
import pl.todo.User.UserService.UserServiceImpl;

@RestController
public class UserController {

    private final UserServiceImpl userServicel;

    public UserController(UserServiceImpl userServicel) {
        this.userServicel = userServicel;
    }



}
