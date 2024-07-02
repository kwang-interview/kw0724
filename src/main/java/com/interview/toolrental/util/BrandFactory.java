package com.interview.toolrental.util;

import com.interview.toolrental.model.Brand;

public class BrandFactory {
    public static Brand generateLadder() {
        return Brand.builder()
                .dailyCharge("1.99")
                .weekdayCharge(true)
                .weekendCharge(true)
                .holidayCharge(true)
                .build();
    }

    public static Brand generateChainsaw() {
        return Brand.builder()
                .dailyCharge("1.49")
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(true)
                .build();
    }

    public static Brand generateJackhammer() {
        return Brand.builder()
                .dailyCharge("2.99")
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
    }
}
