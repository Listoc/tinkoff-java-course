package edu.hw3.task5;

import java.util.Arrays;
import java.util.Comparator;

public class Task5 {

    public static Person[] parseContacts(String[] array, SortingOrder sortingOrder) {
        if (array == null || sortingOrder == null) {
            return new Person[0];
        }

        Comparator<Person> order =
            sortingOrder == SortingOrder.ASC
            ? Comparator.naturalOrder()
            : Comparator.reverseOrder();

        var personArray = toPersonArray(array);

        Arrays.sort(personArray, order);

        return personArray;
    }

    private static Person[] toPersonArray(String[] array) {
        var personArray = new Person[array.length];

        for (int i = 0; i < array.length; ++i) {

            personArray[i] = toPerson(array[i]);

            if (personArray[i] == null) {
                return new Person[0];
            }
        }

        return personArray;
    }

    private static Person toPerson(String personInfo) {
        String[] split = personInfo.split(" ");

        if (split.length == 2 && !split[0].isEmpty()) {
            return new Person(split[0], split[1]);
        } else if (split.length == 1 && !split[0].isEmpty()) {
            return new Person(split[0]);
        }

        return null;
    }


    private Task5() {}
}
