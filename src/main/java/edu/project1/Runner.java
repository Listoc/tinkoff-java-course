package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int MAX_ATTEMPTS = 5;
    private final Dictionary dictionary;

    public Runner(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        Session session;
        LOGGER.info("Добро пожаловать в виселицу!");
        LOGGER.info("Если вы хотите досрочно остановить сессию, то введите слово \"стоп\"");
        boolean choice = true;
        do {
            String word = dictionary.getRandomWord();

            if (word == null) {
                LOGGER.info("Нет слов!");
                break;
            }

            session = new Session(word, MAX_ATTEMPTS);

            do {
                session.guess();
            } while (!session.isOver());

            choice = askForAnotherGame();

        } while (choice);
    }

    private boolean askForAnotherGame() {
        var in = new Scanner(System.in);

        LOGGER.info("Ещё раз? y/n");

        String choice = in.next();

        return choice.equalsIgnoreCase("y");
    }

//    public static void main(String[] args) {
//        var dictionary = new Dictionary();
//        var runner = new Runner(dictionary);
//
//        runner.run();
//    }
}
