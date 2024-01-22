package pl.todo.User.Service;

import pl.todo.User.Model.UserDto;

public interface UserService {

    UserDto findUser(String name);

}
