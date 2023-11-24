package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabase35 implements PersonDatabase {
    private static final int SLEEP_TIME = 1000;

    private final Map<String, List<Person>> mapByName = new HashMap<>();
    private final Map<String, List<Person>> mapByAddress = new HashMap<>();
    private final Map<String, List<Person>> mapByPhone = new HashMap<>();
    private final Map<Integer, Person> mapById = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(Person person) {
        //Добавил sleep для имитации долгой записи и для тестов
        try {
            lock.writeLock().lock();

            if (!mapByName.containsKey(person.name())) {
                mapByName.put(person.name(), new ArrayList<>());
            }
            if (!mapByAddress.containsKey(person.address())) {
                mapByAddress.put(person.address(), new ArrayList<>());
            }
            if (!mapByPhone.containsKey(person.phoneNumber())) {
                mapByPhone.put(person.phoneNumber(), new ArrayList<>());
            }

            mapByName.get(person.name()).add(person);
            Thread.sleep(SLEEP_TIME);

            mapByAddress.get(person.address()).add(person);
            Thread.sleep(SLEEP_TIME);

            mapByPhone.get(person.phoneNumber()).add(person);
            Thread.sleep(SLEEP_TIME);

            mapById.put(person.id(), person);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void delete(int id) {
        try {
            lock.writeLock().lock();
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
        } finally {
            lock.writeLock().unlock();
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

    public List<Person> findByName(String name) {
        try {
            lock.readLock().lock();
            return mapByName.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Person> findByAddress(String address) {
        try {
            lock.readLock().lock();
            return mapByAddress.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Person> findByPhone(String phone) {
        try {
            lock.readLock().lock();
            return mapByPhone.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
