package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {
    @AfterAll
    @BeforeAll
    static void clear() throws IOException {
        Files.deleteIfExists(Path.of("vault.txt"));
    }

    @Test
    void putTest() {
        var map = new DiskMap();
        map.put("testKey", "testValue");

        assertThat(map.entrySet()).isEqualTo(Set.of(MapEntry.entry("testKey", "testValue")));
    }

    @Test
    void sizeTest() {
        var map = new DiskMap();
        map.put("testKey", "testValue");

        assertThat(map.size()).isEqualTo(1);
    }

    @Test
    void getTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.get("testKey1")).isEqualTo("testValue1");
    }

    @Test
    void containsKeyTrueTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.containsKey("testKey2")).isTrue();
    }

    @Test
    void containsKeyFalseTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.containsKey("testKey3")).isFalse();
    }

    @Test
    void containsValueTrueTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.containsValue("testValue1")).isTrue();
    }

    @Test
    void containsValueFalseTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.containsValue("testValue3")).isFalse();
    }

    @Test
    void removeTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        map.remove("testKey1");

        assertThat(map.containsKey("testKey1")).isFalse();
    }

    @Test
    void clearTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        map.clear();

        assertThat(map.isEmpty()).isTrue();
    }

    @Test
    void keySetTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.keySet()).isEqualTo(Set.of("testKey1", "testKey2"));
    }

    @Test
    void valuesTest() {
        var map = new DiskMap();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");

        assertThat(map.values()).containsExactlyInAnyOrderElementsOf(Set.of("testValue1", "testValue2"));
    }

    @Test
    void putAllTest() {
        var map = new DiskMap();
        var hashMap = new HashMap<String, String>();
        map.put("testKey3", "testValue3");
        hashMap.put("testKey1", "testValue1");
        hashMap.put("testKey2", "testValue2");
        map.putAll(hashMap);

        assertThat(map.entrySet()).containsExactlyInAnyOrderElementsOf(Set.of(
            MapEntry.entry("testKey1", "testValue1"),
            MapEntry.entry("testKey2", "testValue2"),
            MapEntry.entry("testKey3", "testValue3")

        ));
    }

    @Test
    void writeToDiskTest() throws IOException {
        var map = new DiskMap();
        var hashMap = new HashMap<String, String>();
        map.put("testKey1", "testValue1");
        map.put("testKey2", "testValue2");
        map.writeToDisk();

        try (var reader = new BufferedReader(new FileReader("vault.txt"))) {
            while (reader.ready()) {
                var split = reader.readLine().split(":");
                hashMap.put(split[0], split[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(map.entrySet()).containsExactlyInAnyOrderElementsOf(hashMap.entrySet());
    }

    @Test
    void readFromDiskTest() throws IOException {
        var map = new DiskMap();
        var hashMap = new HashMap<String, String>();
        hashMap.put("testKey1", "testValue1");
        hashMap.put("testKey2", "testValue2");

        try (var writer = new BufferedWriter(new FileWriter("vault.txt"))) {
            for (var key : hashMap.keySet()) {
                writer.write(key + ':' + hashMap.get(key));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        map.readFromDisk();

        assertThat(map.entrySet()).containsExactlyInAnyOrderElementsOf(hashMap.entrySet());
    }

    @Test
    void readFromBadDiskTest() {
        var map = new DiskMap();
        var hashMap = new HashMap<String, String>();
        hashMap.put("testKey1", "testValue1");
        hashMap.put("testKey2", "testValue2");

        try (var writer = new BufferedWriter(new FileWriter("vault.txt"))) {
            for (var key : hashMap.keySet()) {
                writer.write(key);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThatThrownBy(() -> map.readFromDisk()).isInstanceOf(IOException.class);
    }

}
