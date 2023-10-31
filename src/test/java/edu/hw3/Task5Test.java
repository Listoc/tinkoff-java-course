package edu.hw3;

import edu.hw3.task5.Person;
import edu.hw3.task5.SortingOrder;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    static Arguments[] properInput() {
        return new Arguments[] {
            Arguments.of(
                new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes" },
                SortingOrder.ASC,
                new Person[] {
                    new Person("Thomas", "Aquinas"),
                    new Person("Rene", "Descartes"),
                    new Person("David", "Hume"),
                    new Person("John", "Locke"),
                }
            ),
            Arguments.of(
                new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes" },
                SortingOrder.DESC,
                new Person[] {
                    new Person("John", "Locke"),
                    new Person("David", "Hume"),
                    new Person("Rene", "Descartes"),
                    new Person("Thomas", "Aquinas"),
                }
            ),
            Arguments.of(
                new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                SortingOrder.DESC,
                new Person[] {
                    new Person("Carl", "Gauss"),
                    new Person("Leonhard", "Euler"),
                    new Person("Paul", "Erdos"),
                }
            ),
            Arguments.of(
                new String[]{"Adam", "Thomas Aquinas", "David Hume", "Rene Descartes" },
                SortingOrder.ASC,
                new Person[] {
                    new Person("Adam"),
                    new Person("Thomas", "Aquinas"),
                    new Person("Rene", "Descartes"),
                    new Person("David", "Hume"),
                }
            ),
            Arguments.of(
                new String[]{"Thomas Aquinas"},
                SortingOrder.ASC,
                new Person[] {
                    new Person("Thomas", "Aquinas"),
                }
            )
        };
    }

    //Также в этом тесте проверяется случай, если есть только имя (4й набор аргументов)
    @ParameterizedTest
    @MethodSource("properInput")
    void properInput(String[] array, SortingOrder sortingOrder, Person[] excpected) {
        assertThat(Task5.parseContacts(array, sortingOrder)).isEqualTo(excpected);
    }

    @Test
    void nullInput() {
        assertThat(Task5.parseContacts(null, null)).isEqualTo(new Person[0]);
    }

    @Test
    void emptyArrayInput() {
        assertThat(Task5.parseContacts(new String[0], SortingOrder.ASC)).isEqualTo(new Person[0]);
    }

    static Arguments[] wrongInput() {
        return new Arguments[] {
            Arguments.of(
                new String[]{"", "Thomas Aquinas"},
                SortingOrder.ASC
            ),
            Arguments.of(
                new String[]{"Thomas Aquinas", "A B C"},
                SortingOrder.ASC
            ),
        };
    }

    @ParameterizedTest
    @MethodSource("wrongInput")
    void wrongStringsInput(String[] input, SortingOrder sortingOrder) {
        assertThat(Task5.parseContacts(input, sortingOrder)).isEqualTo(new Person[0]);
    }
}
