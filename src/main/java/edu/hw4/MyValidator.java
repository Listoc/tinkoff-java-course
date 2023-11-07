package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public class MyValidator implements Validator {
    private Set<ValidationError> set;

    public Set<ValidationError> validate(Animal animal) {
        if (animal == null) {
            return null;
        }

        set = new HashSet<>();

        addError(checkName(animal));

        addError(checkSex(animal));

        addError(checkAge(animal));

        return set;
    }

    private void addError(ValidationError error) {
        if (error != null) {
            set.add(error);
        }
    }

    private static ValidationError checkName(Animal animal) {
        if (animal.name().isEmpty()) {
            return new ValidationError("name: empty name");
        }

        return null;
    }

    private static ValidationError checkSex(Animal animal) {
        if (animal.sex() == null) {
            return new ValidationError("sex: null sex");
        }

        return null;
    }

    private static ValidationError checkAge(Animal animal) {
        if (animal.age() < 0) {
            return new ValidationError("age: negative age");
        }

        return null;
    }
}
