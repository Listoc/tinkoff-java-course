package edu.hw2.task4;

public class Task4 {
    public static CallingInfo callingInfo() {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
        }
    }

    private Task4() {}
}
