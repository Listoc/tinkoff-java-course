package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hw4 {
    private static final int MIN_HEIGHT = 100;

    //Task1
    public static List<Animal> sortByHeightAsc(List<Animal> animalsList) {
        return animalsList.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    //Task2
    public static List<Animal> sortByWeightDesc(List<Animal> animalsList) {
        return animalsList.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).toList();
    }

    //Task3
    public static Map<Animal.Type, Long> countAnimalsByType(List<Animal> animalsList) {
        return animalsList.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    //Task4
    public static Animal getAnimalWithLongestName(List<Animal> animalsList) {
        return animalsList.stream().max(Comparator.comparingInt((Animal a) -> a.name().length())).orElseThrow();
    }

    //Task5
    public static Animal.Sex getSexWithMostAnimals(List<Animal> animalsList) {
        var map = animalsList.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        if (map.get(Animal.Sex.F) > map.get(Animal.Sex.M)) {
            return Animal.Sex.F;
        } else {
            return Animal.Sex.M;
        }
    }

    //Task6
    public static Map<Animal.Type, Animal> getHeaviestAnimalOfEachType(List<Animal> animalsList) {
        return animalsList.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                        Optional::get
                    )
                ));
    }

    //Task7
    public static Animal getOldestAnimal(List<Animal> animalsList) {
        return animalsList.stream().max(Comparator.comparingInt(Animal::age)).orElseThrow();
    }

    //Task8
    public static Optional<Animal> getHeaviestAnimalLowerThanKCm(List<Animal> animalsList, int k) {
        return animalsList.stream().filter(a -> a.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    //Task9
    public static Integer getCountOfPaws(List<Animal> animalList) {
        return animalList.stream().mapToInt(Animal::paws).sum();
    }

    //Task10
    public static List<Animal> getAnimalsPawsNotEqualsAge(List<Animal> animalList) {
        return animalList.stream().filter((a) -> a.paws() != a.age()).toList();
    }

    //Task11
    public static List<Animal> getAnimalsThatBitesAndHeightOver100CM(List<Animal> animalList) {
        return animalList.stream().filter((a) -> a.bites() && a.height() > MIN_HEIGHT).toList();
    }

    //Task12
    public static int getCountOfAnimalsWeightOverHeight(List<Animal> animalList) {
        return (int) animalList.stream().filter((a) -> a.weight() > a.height()).count();
    }

    //Task13
    public static List<Animal> getAnimalsMoreThanTwoWordInName(List<Animal> animalList) {
        return animalList.stream().filter((a) -> a.name().split(" ").length > 2).toList();
    }

    //Task14
    public static boolean isHereDogWithHeightOverKCM(List<Animal> animalList, int k) {
        return animalList.stream().anyMatch((a) -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    //Task15
    public static int getSummaryWeightOfAllAnimalsFromKToLAge(List<Animal> animalList, int k, int l) {
        return animalList.stream().filter((a) -> a.age() >= k && a.age() < l).mapToInt(Animal::weight).sum();
    }

    //Task16
    public static List<Animal> sortByTypeThenSexThenName(List<Animal> animalList) {
        return animalList.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();
    }

    private Hw4() {}
}
