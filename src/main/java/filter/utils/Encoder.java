package filter.utils;

import java.util.Base64;

public class Encoder {

    public static String encode(String word) {
        return Base64.getEncoder().encodeToString(word.getBytes());
    }

    public static String decode(String word) {
        byte[] bytes = Base64.getDecoder().decode(word);
        return new String(bytes);
    }
}