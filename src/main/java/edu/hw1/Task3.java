package edu.hw1;

public class Task3 {

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException("Null array");
        }

        if (a1.length == 0 || a2.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }

        return min(a1) > min(a2) && max(a1) < max(a2);
    }

    private static int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; ++i) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    private static int min(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; ++i) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    private Task3() {}
}
