package edu.hw4;

import java.util.Set;

public interface Validator {
    Set<ValidationError> validate(Animal animal);
}
