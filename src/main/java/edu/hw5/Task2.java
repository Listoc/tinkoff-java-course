package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {
    public static List<LocalDate> findAllFridaysThirteenthByYear(int year) {
        if (year < LocalDate.MIN.getYear() || year > LocalDate.MAX.getYear()) {
            return null;
        }

        var list = new LinkedList<LocalDate>();
        var currentDate = LocalDate.of(year, 1, 13);
        while (currentDate.getYear() == year) {
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                list.add(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth()));
            }

            currentDate = currentDate.plusMonths(1);
        }

        return list;
    }

    public static LocalDate findNextFridayThirteenth(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Null input!");
        }

        return date.with(TemporalAdjusters.ofDateAdjuster((d) -> {
            var currentDate = LocalDate.of(d.getYear(), d.getMonth(), 13);
            if (d.getDayOfMonth() > 13) {
                currentDate = currentDate.plusMonths(1);
            }

            while (true) {
                if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY && currentDate.getDayOfMonth() == 13) {
                    return currentDate;
                }

                currentDate = currentDate.plusMonths(1);
            }
        }));
    }

    private Task2() {}
}
