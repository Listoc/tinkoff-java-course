package edu.hw8.task3;


public class PasswordGenerator {
    private static final String SET_OF_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private String lastPassword = "";
    private int lastChangedIndex = -1;

    public synchronized String nextPassword() {
        StringBuilder password = new StringBuilder();

        if (lastPassword.isEmpty()) {
            lastPassword = "a";
            return lastPassword;
        }

        if (lastPassword.equals("9".repeat(lastPassword.length()))) {
            lastPassword = "a".repeat(lastPassword.length() + 1);
            return lastPassword;
        }

        var lastPasswordArray = lastPassword.toCharArray();
        char ch;
        for (int i = 0; i < lastPasswordArray.length; ++i) {
            ch = lastPasswordArray[i];

            if (ch == '9' && lastChangedIndex > i) {
                password.append("a");
                continue;
            } else if (ch == '9' && lastChangedIndex == 0) {
                password.append('a');
                continue;
            } else if (ch == '9') {
                password.append(ch);
                continue;
            }

            lastChangedIndex = i;
            password.append(SET_OF_CHARS.charAt(SET_OF_CHARS.indexOf(ch) + 1));
            break;
        }

        if (lastChangedIndex != -1) {
            password.append(lastPassword.substring(lastChangedIndex + 1));
        }

        lastPassword = password.toString();
        return lastPassword;
    }

    public void clear() {
        lastPassword = "";
        lastChangedIndex = -1;
    }
}
