package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

@SuppressWarnings("CyclomaticComplexity")
public class RandomObjectGenerator implements ObjectGenerator {
    private static final int MAX_PROBABILITY = 100;
    private static final int NOT_NULL_PROBABILITY = 95;
    private final Random random = new Random();

    @Override
    public Object nextObject(@org.jetbrains.annotations.NotNull Class<?> classObject) {
        Constructor<?> constructor = Arrays
            .stream(classObject.getConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow(() -> new IllegalArgumentException("No public constructors"));

        var arguments = randomArgs(constructor.getParameterTypes(), constructor.getParameterAnnotations());

        try {
            return constructor.newInstance(arguments);
        } catch (Exception e) {
            throw new RuntimeException("Can't invoke constructor" + e);
        }
    }

    @Override
    public Object nextObject(
        @org.jetbrains.annotations.NotNull Class<?> classObject,
        @org.jetbrains.annotations.NotNull String factoryMethod
    ) {
        Method factory;
        factory = Arrays.stream(classObject.getMethods())
            .filter((m) -> m.getName().equals(factoryMethod))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No such factory method!"));


        var arguments = randomArgs(factory.getParameterTypes(), factory.getParameterAnnotations());

        try {
            return factory.invoke(null, arguments);
        } catch (Exception e) {
            throw new RuntimeException("Can't invoke method!");
        }
    }

    private Object[] randomArgs(Class<?>[] parameters, Annotation[][] annotations) {
        var list = new LinkedList<>();

        for (int i = 0; i < parameters.length; ++i) {
            list.add(random(parameters[i], annotations[i]));
        }

        return list.toArray(new Object[0]);
    }

    private Object random(Class<?> arg, Annotation[] annotations) {
        Object result;

        if (arg == Integer.class || arg == int.class) {
            result = randomInt(annotations);
        } else if (arg == Double.class || arg == double.class) {
            result = random.nextDouble();
        } else if (arg == Character.class || arg == char.class) {
            result = (char) random.nextInt(Character.MIN_CODE_POINT, Character.MAX_CODE_POINT + 1);
        } else if (arg == Long.class || arg == long.class) {
            result = random.nextLong();
        } else if (arg == Byte.class || arg == byte.class) {
            result = (byte) random.nextInt(Byte.MAX_VALUE, Byte.MAX_VALUE + 1);
        } else if (arg == Short.class || arg == short.class) {
            result = (short) random.nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);
        } else if (arg == Boolean.class || arg == boolean.class) {
            result = random.nextBoolean();
        } else if (arg == Float.class || arg == float.class) {
            result = random.nextFloat();
        } else {
            result = randomObject(arg, annotations);
        }

        return result;
    }

    private Integer randomInt(Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (var annotation : annotations) {
            if (annotation.annotationType().equals(Min.class)) {
                min = ((Min) annotation).value();
            }

            if (annotation.annotationType().equals(Max.class)) {
                max = ((Max) annotation).value();
            }
        }
        return random.nextInt(min, max);
    }

    private Object randomObject(Class<?> arg, Annotation[] annotations) {
        if (
            random.nextInt(0, MAX_PROBABILITY) > NOT_NULL_PROBABILITY
                && Arrays.stream(annotations).noneMatch((a) -> a.annotationType().equals(NotNull.class))
        ) {
            return null;
        } else {
            return nextObject(arg);
        }
    }
}
