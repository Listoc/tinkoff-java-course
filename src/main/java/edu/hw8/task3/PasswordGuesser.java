package edu.hw8.task3;

import java.util.Map;

public interface PasswordGuesser {
    Map<String, String> guessPassword(Map<String, String> persons);
}
