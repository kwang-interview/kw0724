package com.interview.toolrental.service;

import com.interview.toolrental.model.RentalAgreement;

import java.time.LocalDate;

public interface IRentalService {
    public RentalAgreement checkout(
            String toolCode,
            int rentalDays,
            int discountPercent,
            LocalDate checkoutDate
    ) throws Exception;
}
