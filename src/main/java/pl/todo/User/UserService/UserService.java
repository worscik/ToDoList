package pl.todo.User.UserService;

import pl.todo.User.Model.User;
import pl.todo.User.Model.UserDto;

public interface UserService {

    boolean checkUserCredentials();
    User findUser(String externalId);
    UserDto addUser(UserDto userDto);
    UserDto editUser(UserDto userDto);
    boolean removeUser(UserDto userDto);
    String newPassword(UserDto userDto);

}
