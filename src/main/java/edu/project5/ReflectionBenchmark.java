package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
//import org.openjdk.jmh.runner.Runner;
//import org.openjdk.jmh.runner.RunnerException;
//import org.openjdk.jmh.runner.options.Options;
//import org.openjdk.jmh.runner.options.OptionsBuilder;
//import org.openjdk.jmh.runner.options.TimeValue;
//import java.util.concurrent.TimeUnit;
//import org.openjdk.jmh.annotations.Mode;

@State(Scope.Thread)
public class ReflectionBenchmark {
//    public static void main(String[] args) throws RunnerException {
//        Options options = new OptionsBuilder()
//            .include(ReflectionBenchmark.class.getSimpleName())
//            .shouldFailOnError(true)
//            .shouldDoGC(true)
//            .mode(Mode.AverageTime)
//            .timeUnit(TimeUnit.NANOSECONDS)
//            .forks(1)
//            .warmupForks(1)
//            .warmupIterations(1)
//            .warmupTime(TimeValue.seconds(20))
//            .measurementIterations(1)
//            .measurementTime(TimeValue.seconds(120))
//            .build();
//
//        new Runner(options).run();
//    }

    private static final String METHOD_NAME = "name";
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaHandle;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod(METHOD_NAME);
        methodHandle = MethodHandles.lookup()
            .findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));
        lambdaHandle = (Function<Student, String>)
            LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                MethodType.methodType(String.class, Student.class))
                .getTarget()
                .invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflectionMethod(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void reflectionMethodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void reflectionMetaFactory(Blackhole bh) throws Throwable {
        String name = lambdaHandle.apply(student);
        bh.consume(name);
    }

    record Student(String name, String surname) {
    }
}
