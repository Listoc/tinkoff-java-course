package edu.hw4;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

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

    private final static Animal animalCountOfMonteCristo = new Animal(
        "Count of Monte Cristo",
        Animal.Type.DOG,
        Animal.Sex.M,
        5,
        120,
        130,
        true
    );

    private final static Animal animalIkar = new Animal(
        "Ikar",
        Animal.Type.BIRD,
        Animal.Sex.M,
        2,
        30,
        5,
        true
    );

    private final static Animal animalKatya = new Animal(
        "Katya",
        Animal.Type.FISH,
        Animal.Sex.F,
        40,
        110,
        60,
        false
    );

    static {
        animalList = new LinkedList<>();
        animalList.add(animalIvan);
        animalList.add(animalCountOfMonteCristo);
        animalList.add(animalIkar);
        animalList.add(animalKatya);
    }

    @Nested
    class Task1Test {
        @Test
        void properInput() {
            var expected = List.of(animalIkar, animalIvan, animalKatya, animalCountOfMonteCristo);

            assertThat(Hw4.sortByHeightAsc(animalList)).isEqualTo(expected);
        }
    }

    @Nested
    class Task2Test {
        @Test
        void properInput() {
            var expected = List.of(animalCountOfMonteCristo, animalKatya, animalIvan);

            assertThat(Hw4.sortByWeightDesc(animalList, 3)).isEqualTo(expected);
        }

        @Test
        void limitOverSizeInput() {
            var expected = List.of(animalCountOfMonteCristo, animalKatya, animalIvan, animalIkar);

            assertThat(Hw4.sortByWeightDesc(animalList, 5)).isEqualTo(expected);
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
            assertThat(Hw4.getAnimalWithLongestName(animalList)).isEqualTo(animalCountOfMonteCristo);
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
            map.put(Animal.Type.DOG, animalCountOfMonteCristo);
            map.put(Animal.Type.FISH, animalKatya);
            map.put(Animal.Type.BIRD, animalIkar);
            assertThat(Hw4.getHeaviestAnimalOfEachType(animalList)).isEqualTo(map);
        }
    }

    @Nested
    class Task7Test {
        @Test
        void properInput1() {
            assertThat(Hw4.getKOldestAnimal(animalList, 1)).isEqualTo(animalKatya);
        }

        @Test
        void properInput2() {
            assertThat(Hw4.getKOldestAnimal(animalList, 3)).isEqualTo(animalCountOfMonteCristo);
        }

        @Test
        void wrongInput() {
            assertThat(Hw4.getKOldestAnimal(animalList, 6)).isEqualTo(null);
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

    @Nested
    class Task9Test {
        @Test
        void properInput() {
            assertThat(Hw4.getCountOfPaws(animalList)).isEqualTo(10);
        }
    }

    @Nested
    class Task10Test {
        List<Animal> expected = List.of(animalKatya, animalCountOfMonteCristo, animalIvan);
        @Test
        void properInput() {
            assertThat(Hw4.getAnimalsPawsNotEqualsAge(animalList)).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Nested
    class Task11Test {
        List<Animal> expected = List.of(animalCountOfMonteCristo);
        @Test
        void properInput() {
            assertThat(Hw4.getAnimalsThatBitesAndHeightOver100CM(animalList)).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Nested
    class Task12Test {
        @Test
        void properInput() {
            assertThat(Hw4.getCountOfAnimalsWeightOverHeight(animalList)).isEqualTo(1);
        }
    }

    @Nested
    class Task13Test {
        List<Animal> expected = List.of(animalCountOfMonteCristo);
        @Test
        void properInput() {
            assertThat(Hw4.getAnimalsMoreThanTwoWordInName(animalList)).containsExactlyInAnyOrderElementsOf(expected);
        }
    }

    @Nested
    class Task14Test {
        @Test
        void properInputForTrue() {
            assertThat(Hw4.isHereDogWithHeightOverKCM(animalList, 100)).isTrue();
        }

        @Test
        void properInputForFalse() {
            assertThat(Hw4.isHereDogWithHeightOverKCM(animalList, 200)).isFalse();
        }
    }

    @Nested
    class Task15Test {
        @Test
        void properInput() {
            var expected = new HashMap<Animal.Type, Integer>();
            expected.put(Animal.Type.DOG, 150);
            expected.put(Animal.Type.BIRD, 5);
            assertThat(Hw4.getSummaryWeightOfAllAnimalsFromKToLAgeByType(animalList, 1, 30)).containsAllEntriesOf(expected);
        }

        @Test
        void noSuchAnimals() {
            assertThat(Hw4.getSummaryWeightOfAllAnimalsFromKToLAgeByType(animalList, 1, 1)).isEqualTo(new HashMap<>());
        }
    }

    @Nested
    class Task16Test {
        @Test
        void properInput() {
            List<Animal> expected = List.of(animalCountOfMonteCristo, animalIvan, animalIkar, animalKatya);
            assertThat(Hw4.sortByTypeThenSexThenName(animalList)).isEqualTo(expected);
        }
    }

    @Nested
    class Task17Test {
        @Test
        void properInputForFalse() {
            assertThat(Hw4.doSpidersBitesOftenThenDogs(animalList)).isFalse();
        }

        @Test
        void properInputForTrue() {
            List<Animal> test = List.of(
                animalIvan,
                animalIkar,
                new Animal("Oleg", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true),
                new Animal("Vasya", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true));
            assertThat(Hw4.doSpidersBitesOftenThenDogs(test)).isTrue();
        }

        @Test
        void properInputEqualsCount() {
            List<Animal> test = List.of(
                animalIvan,
                animalCountOfMonteCristo,
                new Animal("Oleg", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true),
                new Animal("Vasya", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true));
            assertThat(Hw4.doSpidersBitesOftenThenDogs(test)).isFalse();
        }
    }

    @Nested
    class Task18Test {
        @Test
        void properInputFound() {
            var fish1 = getFish(5);
            var fish2 = getFish(70);
            var fish3 = getFish(15);

            List<List<Animal>> input = List.of(
                List.of(animalIkar, animalIvan),
                List.of(fish1, animalKatya, fish2),
                List.of(animalCountOfMonteCristo, fish3)
            );

            assertThat(Hw4.getHeaviestFishFromAnimalsLists(input)).isEqualTo(fish2);
        }

        @Test
        void properInputNotFound() {
            List<List<Animal>> input = List.of(
                List.of(animalIkar, animalIvan),
                List.of(animalCountOfMonteCristo)
            );

            assertThat(Hw4.getHeaviestFishFromAnimalsLists(input)).isEqualTo(null);
        }

        public static Animal getFish(int weight) {
            return new Animal(
                "Test",
                Animal.Type.FISH,
                Animal.Sex.M,
                1,
                1,
                weight,
                false
            );
        }
    }

    @Nested
    class Task19Test {
        private final List<Animal> input = List.of(
            animalIkar,
            new Animal("", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true),
            new Animal("Test", Animal.Type.FISH, null, 1, 1, 1, true),
            new Animal("Test2", Animal.Type.FISH, null, -1, 1, 1, true)
        );

        @Test
        void properInput() {
            Set<ValidationError> set;
            var expected = new HashMap<String, Set<ValidationError>>();

            set = new HashSet<>();
            set.add(new ValidationError("name: empty name"));
            expected.put("", set);

            set = new HashSet<>();
            set.add(new ValidationError("sex: null sex"));
            expected.put("Test", set);

            set = new HashSet<>();
            set.add(new ValidationError("sex: null sex"));
            set.add(new ValidationError("age: negative age"));
            expected.put("Test2", set);

            assertThat(Hw4.getAnimalsWithErrorsSet(input)).isEqualTo(expected);
        }
    }

    @Nested
    class Task20Test {
        private final List<Animal> input = List.of(
            animalIkar,
            new Animal("", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true),
            new Animal("Test", Animal.Type.FISH, null, 1, 1, 1, true),
            new Animal("Test2", Animal.Type.FISH, null, -1, 1, 1, true)
        );

        @Test
        void properInput() {
            var expected = new HashMap<String, String>();

            expected.put("", "name: empty name\n");

            expected.put("Test", "sex: null sex\n");

            expected.put("Test2", "age: negative age\nsex: null sex\n");

            assertThat(Hw4.getAnimalsWithErrorsString(input)).isEqualTo(expected);
        }
    }
}
