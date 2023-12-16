package edu.hw10.task2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class CacheProxy implements InvocationHandler {
    private final Map<String, Object> cache;
    private final Path dir;
    private final Object object;

    private CacheProxy(Object object) {
        cache = new HashMap<>();
        try {
            dir = Files.createTempDirectory("cacheDir");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.object = object;
    }

    public static Object create(@NotNull Object object) {
        return
            Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new CacheProxy(object)
            );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;

        if (method.isAnnotationPresent(Cache.class)) {
            var annotation = method.getAnnotation(Cache.class);
            boolean persist = annotation.persist();
            String name = method.getName() + Arrays.toString(args);

            if (persist) {
                if (Files.exists(dir.resolve(name))) {
                    result = getFromDisk(name);
                } else {
                    result = method.invoke(object, args);
                    saveToDisk(name, result);
                }
                return result;

            } else {
                if (cache.containsKey(name)) {
                    result = cache.get(name);
                } else {
                    result = method.invoke(object, args);
                    cache.put(name, result);
                }
            }

        } else {
            result = method.invoke(object, args);
        }

        return result;
    }

    private Object getFromDisk(String name) {
        try (
            var inputStream = Files.newInputStream(dir.resolve(name));
            var objectInputStream = new ObjectInputStream(inputStream);
            ) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToDisk(String name, Object object) {
        try (
            var outputStream = Files.newOutputStream(dir.resolve(name));
            var objectOutputStream = new ObjectOutputStream(outputStream);
            ) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
