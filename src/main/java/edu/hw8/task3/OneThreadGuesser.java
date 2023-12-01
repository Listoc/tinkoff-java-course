package edu.hw8.task3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class OneThreadGuesser implements PasswordGuesser {
    public Map<String, String> guessPassword(Map<String, String> persons) {
        var generator = new PasswordGenerator();
        var result = new HashMap<String, String>();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm problems");
        }
        String hash;
        String password;
        while (result.size() != persons.size()) {
            password = generator.nextPassword();

            md.update(password.getBytes(StandardCharsets.UTF_8));
            hash = String.format("%032x", new BigInteger(1, md.digest()));

            if (persons.containsKey(hash)) {
                result.put(persons.get(hash), password);
            }
        }

        return result;
    }
}
