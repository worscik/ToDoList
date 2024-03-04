package pl.todo.User.UserService;

import org.springframework.stereotype.Service;
import pl.todo.User.Model.*;
import pl.todo.User.UserRepository.UserDao;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final UserMapper userMapper;



    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public boolean resolveUser(String login, String password) {
        if(Objects.isNull(password) || Objects.isNull(login)){
            return false;
        }
        return userDao.findUserByCredentials(login,password);
    }

    @Override
    public User findUser(String externalId) {
        Optional<User> user = userDao.findUserByExternalId(externalId);
        return user.orElse(null);
    }

    @Override
    public UserResponse addUser(String email, String password) {
        return Optional.ofNullable(userDao.findUserByEmail(email))
                .map(user -> UserResponse.createResponse(null, "User is exist."))
                .orElseGet(() -> {
                    User newUser = User.createUser(UUID.randomUUID(), email, password, UserRole.USER);
                    userDao.add(newUser);
                    return UserResponse.createResponse(userMapper.userMapper(newUser), "User added to system.");
                });
    }

    @Override
    public UserResponse editUser(UserDto userDto) {
        Optional<User> isUserExist = Optional.ofNullable(userDao.findUserByEmail(userDto.getEmail()));
        if (isUserExist.isPresent()) {
            User user = isUserExist.get();
            return UserResponse.createResponse(userMapper.userMapper(userDao.edit(user)), "");
        } else {
            return UserResponse.createResponse(null, "Can not find user by email: " + userDto.getEmail());
        }
    }

    @Override
    public boolean removeUser(UserDto userDto) {
        return false;
    }

}
