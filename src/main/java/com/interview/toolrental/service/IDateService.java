package com.interview.toolrental.service;

import java.time.LocalDate;

public interface IDateService {
    public LocalDate getIndependenceDay(int year);

    public LocalDate getLaborDay(int year);
}
