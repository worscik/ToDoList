package todo.list.LoginVerify;

import java.security.SecureRandom;
import java.util.Base64;

public class UserToken {

    private String token;

    public UserToken() {
        this.token = createToken();
    }

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public String getToken() {
        return token;
    }

    private String createToken () {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
