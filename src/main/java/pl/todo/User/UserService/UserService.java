package pl.todo.User.UserService;

import pl.todo.User.Model.User;
import pl.todo.User.Model.UserDto;
import pl.todo.User.Model.UserResponse;

public interface UserService {

    boolean resolveUser(String password, String login);
    User findUser(String externalId);
    UserResponse addUser(String login, String password);
    UserResponse editUser(UserDto userDto);
    boolean removeUser(UserDto userDto);

}
