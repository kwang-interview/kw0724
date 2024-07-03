package com.interview.toolrental.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RentalServiceTests {
    @Autowired
    private IRentalService rentalService;

    @Test
    public void interviewTestCases() throws Exception {
        // discount percent error message
        assertThrows(Exception.class, () -> rentalService.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3)));

        var rentalAgreement = rentalService.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
        rentalAgreement.debug();

        rentalAgreement = rentalService.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
        rentalAgreement.debug();

        rentalAgreement = rentalService.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
        rentalAgreement.debug();

        rentalAgreement = rentalService.checkout("JAKR", 5, 0, LocalDate.of(2015, 7, 2));
        rentalAgreement.debug();

        rentalAgreement = rentalService.checkout("JAKR", 5, 50, LocalDate.of(2020, 7, 2));
        rentalAgreement.debug();
    }
}
