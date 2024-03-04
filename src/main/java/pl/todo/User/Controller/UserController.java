package pl.todo.User.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.todo.User.Model.AddUserRequest;
import pl.todo.User.Model.UserResponse;
import pl.todo.User.UserService.UserServiceImpl;

@RestController
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserResponse addUser(@RequestBody AddUserRequest request) {
        return userService.addUser(request.email(), request.password());
    }


}
