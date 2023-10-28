package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    public static <T> Map<T, Integer> freqDict(List<T> input) {
        var map = new HashMap<T, Integer>();
        if (input == null) {
            return map;
        }

        for (var el : input) {
            map.merge(el, 1, Integer::sum);
        }

        return map;
    }

    private Task3() {}
}
