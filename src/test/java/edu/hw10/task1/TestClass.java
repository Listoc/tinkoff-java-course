package edu.hw10.task1;

public class TestClass {
    Integer a1;
    int a2;
    boolean a3;
    char a4;
    InnerTest a5;

    private TestClass(Integer a1, @Max(100) @Min(50) int a2, boolean a3, char a4, InnerTest a5) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
    }

    public static TestClass of(Integer a1, @Max(100) @Min(50) int a2, boolean a3, char a4, @NotNull InnerTest a5, float a6, double a7, Long a8, byte a9, Short a10) {
        return new TestClass(a1, a2, a3, a4, a5);
    }

    public Integer getA1() {
        return a1;
    }

    public int getA2() {
        return a2;
    }

    public boolean isA3() {
        return a3;
    }

    public char getA4() {
        return a4;
    }

    public InnerTest getA5() {
        return a5;
    }
}
