package edu.hw3.task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        if (firstName == null) {
            throw new IllegalArgumentException("Null first name");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName) {
        this(firstName, null);
    }

    @Override
    public int compareTo(@NotNull Person other) {
        String thisNameToCompare = this.lastName == null ? this.firstName : this.lastName;
        String otherNameToCompare = other.lastName == null ? other.firstName : other.lastName;

        return thisNameToCompare.compareTo(otherNameToCompare);
    }

    @Override public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Person person = (Person) other;

        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override public String toString() {
        return "Person{"
            + "firstName='" + firstName + '\''
            + ", lastName='" + lastName + '\''
            + '}';
    }
}
