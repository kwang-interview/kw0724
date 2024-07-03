package com.interview.toolrental.service;

import java.time.LocalDate;
import java.util.List;

public interface IDateService {
    public LocalDate getIndependenceDay(int year);

    public LocalDate getLaborDay(int year);

    public List<LocalDate> getAllHolidays(int year);
}
