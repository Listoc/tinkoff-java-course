package edu.hw2.task4;

public class Task4 {
    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
    }

    private Task4() {}
}
