package edu.hw7;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase3;
import edu.hw7.task3.PersonDatabase35;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Nested
    class Synchronized3Test {
        @Test
        void addTest() {
            var DB = new PersonDatabase3();

            final List<Person> expectedNameList = new ArrayList<>();
            final List<Person> expectedAddressList = new ArrayList<>();
            final List<Person> expectedPhoneList = new ArrayList<>();

            var thread1 = new Thread(() -> DB.add(new Person(2, "name", "address", "phone")));
            var thread2 = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                expectedNameList.addAll(DB.findByName("name"));
                expectedAddressList.addAll(DB.findByAddress("address"));
                expectedPhoneList.addAll(DB.findByPhone("phone"));
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sumSize = expectedNameList.size() + expectedAddressList.size() + expectedPhoneList.size();

            assertThat(sumSize).isEqualTo(3);
        }

        @Test
        void deleteTest() {
            var DB = new PersonDatabase3();

            DB.add(new Person(2, "name", "address", "phone"));

            final List<Person> expectedNameList = new ArrayList<>();
            final List<Person> expectedAddressList = new ArrayList<>();
            final List<Person> expectedPhoneList = new ArrayList<>();

            var thread1 = new Thread(() -> DB.delete(2));
            var thread2 = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                expectedNameList.addAll(DB.findByName("name"));
                expectedAddressList.addAll(DB.findByAddress("address"));
                expectedPhoneList.addAll(DB.findByPhone("phone"));
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sumSize = expectedNameList.size() + expectedAddressList.size() + expectedPhoneList.size();

            assertThat(sumSize).isEqualTo(0);
        }
    }

    @Nested
    class Lock3Test {
        @Test
        void addTest() {
            var DB = new PersonDatabase35();

            final List<Person> expectedNameList = new ArrayList<>();
            final List<Person> expectedAddressList = new ArrayList<>();
            final List<Person> expectedPhoneList = new ArrayList<>();

            var thread1 = new Thread(() -> DB.add(new Person(2, "name", "address", "phone")));
            var thread2 = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                expectedNameList.addAll(DB.findByName("name"));
                expectedAddressList.addAll(DB.findByAddress("address"));
                expectedPhoneList.addAll(DB.findByPhone("phone"));
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sumSize = expectedNameList.size() + expectedAddressList.size() + expectedPhoneList.size();

            assertThat(sumSize).isEqualTo(3);
        }

        @Test
        void deleteTest() {
            var DB = new PersonDatabase35();

            DB.add(new Person(2, "name", "address", "phone"));

            final List<Person> expectedNameList = new ArrayList<>();
            final List<Person> expectedAddressList = new ArrayList<>();
            final List<Person> expectedPhoneList = new ArrayList<>();

            var thread1 = new Thread(() -> DB.delete(2));
            var thread2 = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                expectedNameList.addAll(DB.findByName("name"));
                expectedAddressList.addAll(DB.findByAddress("address"));
                expectedPhoneList.addAll(DB.findByPhone("phone"));
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sumSize = expectedNameList.size() + expectedAddressList.size() + expectedPhoneList.size();

            assertThat(sumSize).isEqualTo(0);
        }
    }
}
