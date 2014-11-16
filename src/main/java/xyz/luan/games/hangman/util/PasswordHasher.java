package xyz.luan.games.hangman.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordHasher {

    private static final String BASE_ENCODING = "UTF-8";

    private PasswordHasher() {
        throw new RuntimeException("Should not be instanciated");
    }

    private static final MessageDigest HASHER;
    static {
        try {
            HASHER = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Couldn't find SHA-256", e);
        }
    }

    public static String hash(String pass) {
        try {
            HASHER.update(pass.getBytes(BASE_ENCODING));
            return new String(HASHER.digest(), BASE_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Couldn't use SHA-256", e);
        }
    }
}
