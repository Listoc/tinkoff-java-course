package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabase3 implements PersonDatabase {
    private static final int SLEEP_TIME = 1000;

    private final Map<String, List<Person>> mapByName = new HashMap<>();
    private final Map<String, List<Person>> mapByAddress = new HashMap<>();
    private final Map<String, List<Person>> mapByPhone = new HashMap<>();
    private final Map<Integer, Person> mapById = new HashMap<>();

    public synchronized void add(Person person) {
        if (!mapByName.containsKey(person.name())) {
            mapByName.put(person.name(), new ArrayList<>());
        }
        if (!mapByAddress.containsKey(person.address())) {
            mapByAddress.put(person.address(), new ArrayList<>());
        }
        if (!mapByPhone.containsKey(person.phoneNumber())) {
            mapByPhone.put(person.phoneNumber(), new ArrayList<>());
        }

        //Добавил sleep для имитации долгой записи и для тестов
        try {
            mapByName.get(person.name()).add(person);
            Thread.sleep(SLEEP_TIME);

            mapByAddress.get(person.address()).add(person);
            Thread.sleep(SLEEP_TIME);

            mapByPhone.get(person.phoneNumber()).add(person);
            Thread.sleep(SLEEP_TIME);

            mapById.put(person.id(), person);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void delete(int id) {
        try {
            var person = mapById.remove(id);
            if (person == null) {
                return;
            }

            Thread.sleep(SLEEP_TIME);

            deleteFromMap(person.id(), person.name(), mapByName);
            Thread.sleep(SLEEP_TIME);

            deleteFromMap(person.id(), person.address(), mapByAddress);
            Thread.sleep(SLEEP_TIME);

            deleteFromMap(person.id(), person.phoneNumber(), mapByPhone);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFromMap(int personId, String personField, Map<String, List<Person>> map) {
        var list = map.get(personField);
        for (var e : list) {
            if (e.id() == personId) {
                list.remove(e);
                break;
            }
        }
    }

    public synchronized List<Person> findByName(String name) {
        return mapByName.get(name);
    }

    public synchronized List<Person> findByAddress(String address) {
        return mapByAddress.get(address);
    }

    public synchronized List<Person> findByPhone(String phone) {
        return mapByPhone.get(phone);
    }
}
