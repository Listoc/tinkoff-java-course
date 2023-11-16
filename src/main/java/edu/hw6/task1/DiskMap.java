package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Map<String, String> map = new HashMap<>();
    private static final String FILE_NAME = "vault.txt";

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public void writeToDisk() throws IOException {
        try (var writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (var key : map.keySet()) {
                writer.write(key + ':' + map.get(key));
                writer.newLine();
            }
        }
    }

    public void readFromDisk() throws IOException {
        try (var reader = new BufferedReader(new FileReader(FILE_NAME))) {
            map.clear();

            while (reader.ready()) {
                var split = reader.readLine().split(":");
                if (split.length != 2) {
                    throw new IOException();
                }
                map.put(split[0], split[1]);
            }

        }
    }
}
