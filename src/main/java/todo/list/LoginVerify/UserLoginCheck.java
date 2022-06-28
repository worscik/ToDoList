package todo.list.LoginVerify;

import com.sun.source.tree.BreakTree;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
public class UserLoginCheck extends UserToken{

    UserToken userToken;

    String filePath = ("/cytrus/bg/user.txt");

    public UserLoginCheck() throws FileNotFoundException {
    }

    public String saveUser(UserLogin userLogin) throws IOException {

        String tok = generateNewToken();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
       writer.write("email: " + userLogin.getEmail() + " " +
                "password:" + userLogin.getPassword() +  " " +
                "token:"+ tok + "\n");
        writer.close();
        return tok;
    }

    @RequestMapping("/userCheck")
    public String LoginVeryfication(@RequestBody UserLogin userLogin) throws IOException {
        return saveUser(userLogin);
    }

}
