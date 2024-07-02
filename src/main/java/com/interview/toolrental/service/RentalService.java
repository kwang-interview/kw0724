package com.interview.toolrental.service;

import com.interview.toolrental.model.RentalAgreement;

import java.time.LocalDate;

public class RentalService implements IRentalService {

    @Override
    public RentalAgreement checkout(
            String toolCode,
            int rentalDays,
            int discountPercent,
            LocalDate checkoutDate
    ) throws Exception {
        if (rentalDays < 1) {
            throw new Exception(String.format("Rental day count [%d] is not 1 or greater", rentalDays));
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new Exception(String.format("Discount percent [%d]is not in the range 0-100", discountPercent));
        }

        return RentalAgreement.builder().build();
    }
}
