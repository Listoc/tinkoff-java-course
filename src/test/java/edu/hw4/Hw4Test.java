package edu.hw4;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Hw4Test {
    private final static List<Animal> animalList;
    private final static Animal animalIvan = new Animal(
        "Ivan",
        Animal.Type.DOG,
        Animal.Sex.M,
        10,
        100,
        20,
        true
    );

    private final static Animal animalVasyan = new Animal(
        "Vasyan",
        Animal.Type.DOG,
        Animal.Sex.M,
        5,
        120,
        30,
        true
    );

    private final static Animal animalIkar = new Animal(
        "Ikar",
        Animal.Type.BIRD,
        Animal.Sex.M,
        2,
        30,
        5,
        false
    );

    private final static Animal animalKatya = new Animal(
        "Katya",
        Animal.Type.FISH,
        Animal.Sex.F,
        40,
        110,
        60,
        true
    );

    static {
        animalList = new LinkedList<>();
        animalList.add(animalIvan);
        animalList.add(animalVasyan);
        animalList.add(animalIkar);
        animalList.add(animalKatya);
    }

    @Nested
    class Task1Test {
        @Test
        void properInput() {
            var expected = List.of(animalIkar, animalIvan, animalKatya, animalVasyan);

            assertThat(Hw4.sortByHeightAsc(animalList)).isEqualTo(expected);
        }
    }

    @Nested
    class Task2Test {
        @Test
        void properInput() {
            var expected = List.of(animalKatya, animalVasyan, animalIvan, animalIkar);

            assertThat(Hw4.sortByWeightDesc(animalList)).isEqualTo(expected);
        }
    }

    @Nested
    class Task3Test {
        @Test
        void properInput() {
            var expected = Map.of(Animal.Type.BIRD, 1L, Animal.Type.DOG, 2L, Animal.Type.FISH, 1L);

            assertThat(Hw4.countAnimalsByType(animalList)).isEqualTo(expected);
        }
    }

    @Nested
    class Task4Test {
        @Test
        void properInput() {
            assertThat(Hw4.getAnimalWithLongestName(animalList)).isEqualTo(animalVasyan);
        }
    }

    @Nested
    class Task5Test {
        @Test
        void properInput() {
            assertThat(Hw4.getSexWithMostAnimals(animalList)).isEqualTo(Animal.Sex.M);
        }
    }

    @Nested
    class Task6Test {
        @Test
        void properInput() {
            var map = new HashMap<Animal.Type, Animal>();
            map.put(Animal.Type.DOG, animalVasyan);
            map.put(Animal.Type.FISH, animalKatya);
            map.put(Animal.Type.BIRD, animalIkar);
            assertThat(Hw4.getHeaviestAnimalOfEachType(animalList)).isEqualTo(map);
        }
    }

    @Nested
    class Task7Test {
        @Test
        void properInput() {
            assertThat(Hw4.getOldestAnimal(animalList)).isEqualTo(animalKatya);
        }
    }

    @Nested
    class Task8Test {
        @Test
        void properInput1() {
            assertThat(Hw4.getHeaviestAnimalLowerThanKCm(animalList, 100).isPresent()).isEqualTo(true);
            assertThat(Hw4.getHeaviestAnimalLowerThanKCm(animalList, 100).get()).isEqualTo(animalIkar);
        }

        @Test
        void properInput2() {
            assertThat(Hw4.getHeaviestAnimalLowerThanKCm(animalList, 101).isPresent()).isEqualTo(true);
            assertThat(Hw4.getHeaviestAnimalLowerThanKCm(animalList, 101).get()).isEqualTo(animalIvan);
        }

        @Test
        void noSuchAnimals() {
            assertThat(Hw4.getHeaviestAnimalLowerThanKCm(animalList, 5).isPresent()).isEqualTo(false);
        }
    }
}
