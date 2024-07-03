package com.interview.toolrental.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class DateService implements IDateService {
    @Override
    public LocalDate getIndependenceDay(int year) {

        var independenceDay = LocalDate.of(year, Month.JULY, 4);

        // if it is on a weekend, return the closest weekday
        var dayOfWeek = DayOfWeek.of(independenceDay.get(ChronoField.DAY_OF_WEEK));
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            independenceDay = LocalDate.of(year, Month.JULY, 3);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            independenceDay = LocalDate.of(year, Month.JULY, 5);
        }

        return independenceDay;
    }

    @Override
    public LocalDate getLaborDay(int year) {
        return LocalDate.of(year, Month.SEPTEMBER, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    @Override
    public List<LocalDate> getAllHolidays(int year) {
        return List.of(getIndependenceDay(year), getLaborDay(year));
    }
}
