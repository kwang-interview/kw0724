package com.interview.toolrental.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtil {
    private DateUtil() {
    }

    public static int calculateWeekends(LocalDate fromDate, LocalDate toDate) {
        var exclusiveFromDate = fromDate.plusDays(1);
        var inclusiveToDate = toDate.plusDays(1);
        return (int) exclusiveFromDate.datesUntil(inclusiveToDate)
                .filter(d -> d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY)
                .count();
    }
}
