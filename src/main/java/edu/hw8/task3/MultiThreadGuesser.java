package edu.hw8.task3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadGuesser implements PasswordGuesser {
    private final int countOfThreads;
    private Map<String, String> persons;
    private PasswordGenerator generator = new PasswordGenerator();
    private Map<String, String> result = new ConcurrentHashMap<>();

    public MultiThreadGuesser(int countOfThreads) {
        this.countOfThreads = countOfThreads;
    }

    public Map<String, String> guessPassword(Map<String, String> persons) {
        generator = new PasswordGenerator();
        result = new ConcurrentHashMap<>();
        this.persons = persons;
        var listOfThreads = new LinkedList<Thread>();
        for (int i = 0; i < countOfThreads; ++i) {
            listOfThreads.add(new Thread(getRunnable()));
        }

        listOfThreads.forEach(Thread::start);
        for (var el : listOfThreads) {
            try {
                el.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Threads interrupted");
            }
        }

        return result;
    }

    public Runnable getRunnable() {
        return () -> {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
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
        };
    }
}
