package todo.list.LoginVerify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@RestController
public class UserLoginCheck {
//    private static final Logger logger = LoggerFactory.getLogger(UserLoginCheck.class);
//    UserToken userToken = new UserToken();
//    Map<String, String> UsersMailAndToken = new TreeMap<>();
//    Map<String, String> UsersMailAndToken1 = new TreeMap<>();
//    Map<String, String> UsersLoginAndPasswords = new TreeMap<>();
//
//    public void addUserToMap(UserLogin userLogin){
//        if(UsersMailAndToken.containsKey(userLogin.getEmail()) || UsersMailAndToken.containsValue(userLogin.getEmail())) {
//            logger.error("Taki użytkownik już istnieje - " + userLogin.getEmail());
//        } else {
//            UsersLoginAndPasswords.put(userLogin.getEmail(), userLogin.getPassword());
//            UsersMailAndToken.put(userLogin.getEmail(), userToken.setToken());
//            UsersMailAndToken1.put(UsersMailAndToken.get(userLogin.getEmail()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ), userLogin.getEmail());
//        }
//
//        }
//
//    @RequestMapping("/userCheck")
//    public String LoginVeryfication(@RequestBody UserLogin userLogin){
//        addUserToMap(userLogin);
//        return UsersMailAndToken.get(userLogin.getEmail());
//    }
//
//    @RequestMapping("/token")
//    public String ewq(@RequestBody UserToken userToken) {
//        return UsersMailAndToken1.get(userToken.getToken());
//    }
//
//    @RequestMapping("/check")
//    public String lista(@RequestBody UserLogin userLogin) {
//       return UsersMailAndToken.get(userLogin.getEmail());
//    }
//
//    @RequestMapping("/test")
//    public Map<String, String> test2(@RequestBody UserLogin userLogin) {
//        return UsersMailAndToken;
//    }
//
//    @RequestMapping("/tokens")
//    public Map<String, String> test1() {
//        return UsersMailAndToken1;
//    }


}
