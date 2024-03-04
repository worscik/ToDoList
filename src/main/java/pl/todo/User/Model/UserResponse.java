package pl.todo.User.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    @JsonProperty("user")
    private UserDto userDto;
    @JsonProperty("result")
    private String result;

    private UserResponse(UserDto userDto, String result) {
        this.userDto = userDto;
        this.result = result;
    }

    public static UserResponse createResponse(UserDto userDto, String result){
        return new UserResponse(userDto,result);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
