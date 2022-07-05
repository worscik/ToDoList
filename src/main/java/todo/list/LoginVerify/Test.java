package todo.list.LoginVerify;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Test {


    UserToken userToken = new UserToken();
    List<UserLogin> testowa = new LinkedList<UserLogin>();

    HashMap<String, UserLogin> users = new HashMap<String, UserLogin>();

    public UserLogin createUser(UserLogin userLogin) {
        if(!users.containsKey(userLogin.getEmail())){
            UserLogin userLogin1 = new UserLogin(userLogin.getEmail(),userLogin.getPassword());
            userLogin1.setUserToken(new UserToken());
            users.put(userLogin.getEmail(),userLogin1);
            return userLogin1;
        }
        return null;
    }









//    HashMap<String, String> hash_map = new HashMap<String, String>();
//    HashMap<String, Map> hash_map1 = new HashMap<String, Map>();



//    public Object add(UserLogin userLogin) {
//        UserLogin userLogin1 = new UserLogin(userLogin.getEmail(), userLogin.getPassword());
//
//        if(hash_map1.containsKey(userLogin.getEmail())){
//            return "Użytkownik już istnieje";
//        } else {
//            hash_map.put(userLogin1.getPassword(),userToken.setToken());
//            hash_map1.put(userLogin.getEmail(),hash_map);
//            return hash_map1.get(userLogin.getEmail());
//        }
//
//    }

    @RequestMapping("/test")
    public Object adder(@RequestBody UserLogin userLogin) {
        UserLogin user = createUser(userLogin);
        if (user != null) {
            return user;
        }
        return "Istnieje";
    }

    @RequestMapping("/token")
    public AbstractMap<String, String> returnToken(UserLogin user){
        return null;
    }

    @RequestMapping("/array")
    public Map<String, Map> elo() {
        return null;
    }



}
