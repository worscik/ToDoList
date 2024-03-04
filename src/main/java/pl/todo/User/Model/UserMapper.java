package pl.todo.User.Model;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto userMapper(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setExternalId(user.getExternalId());
        return userDto;
    }

}
