package filter.utils;

import java.util.Base64;

public class Encoder {

    public static String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}