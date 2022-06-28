package todo.list.LoginVerify;

import java.security.SecureRandom;
import java.util.Base64;

public class UserToken {

    private String token;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
