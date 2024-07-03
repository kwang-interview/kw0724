package com.interview.toolrental.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String dailyRentalCharge;
    private int chargeDays;
    private String prediscountCharge;
    private int discountPercent;
    private String discountAmount;
    private String finalCharge;

    @Override
    public String toString() {
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return String.format("""
                Tool code: %s
                Tool type: %s
                Tool brand: %s
                Rental days: %d
                Check out date: %s
                Due date: %s
                Daily rental charge: $%s
                Charge days: %d
                Pre-discount charge: $%s
                Discount percent: %s%%
                Discount amount: $%s
                Final charge: $%s
                """,
                toolCode,
                toolType,
                toolBrand,
                rentalDays,
                formatter.format(checkoutDate),
                formatter.format(dueDate),
                dailyRentalCharge,
                chargeDays,
                prediscountCharge,
                discountPercent,
                discountAmount,
                finalCharge);
    }

    public void debug() {
        System.out.println(this);
    }
}
