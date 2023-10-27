package edu.hw3.task7;

import java.util.Comparator;

public class Task7 {

    public static <T extends Comparable<T>> Comparator<T> getNullComparator() {
        return Comparator.nullsFirst(Comparable::compareTo);
    }

    private Task7() {}
}
