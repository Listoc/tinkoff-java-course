package edu.hw10.task1;

public interface ObjectGenerator {
    Object nextObject(Class<?> classObject);

    Object nextObject(Class<?> classObject, String factoryMethod);
}
