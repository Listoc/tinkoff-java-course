package edu.hw3.task2;

import java.util.LinkedList;
import java.util.List;

public class Task2 {

    public static List<String> clusterize(String input) {
        var list = new LinkedList<String>();

        if (input == null) {
            return list;
        }

        var builder = new StringBuilder();
        var array = input.toCharArray();
        int openBracesCount = 0;

        for (char c : array) {
            if (c == '(') {
                openBracesCount++;
                builder.append(c);
            } else if (c == ')') {
                if (openBracesCount < 1) {
                    list.clear();
                    break;
                } else {
                    openBracesCount--;
                    builder.append(c);
                }
            } else {
                list.clear();
                break;
            }

            if (openBracesCount == 0) {
                list.add(builder.toString());
                builder.setLength(0);
            }
        }

        if (openBracesCount != 0) {
            list.clear();
        }

        return list;
    }

    private Task2() {}
}
