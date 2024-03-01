package pl.todo.User.UserService;

import pl.todo.User.Model.User;
import pl.todo.User.UserRepository.UserDao;

public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean checkUserCredentials() {
        return false;
    }

    @Override
    public User findUser(String externalId) {
        return null;
    }

    @Override
    public pl.todo.User.Model.UserDto addUser(pl.todo.User.Model.UserDto userDto) {
        return null;
    }

    @Override
    public pl.todo.User.Model.UserDto editUser(pl.todo.User.Model.UserDto userDto) {
        return null;
    }

    @Override
    public boolean removeUser(pl.todo.User.Model.UserDto userDto) {
        return false;
    }

    @Override
    public String newPassword(pl.todo.User.Model.UserDto userDto) {
        return null;
    }
}
