package com.interview.toolrental.util;

import com.interview.toolrental.model.ToolTypeDetail;

public class ToolTypeDetailFactory {
    public static ToolTypeDetail generateLadder() {
        return ToolTypeDetail.builder()
                .dailyCharge("1.99")
                .weekdayCharge(true)
                .weekendCharge(true)
                .holidayCharge(true)
                .build();
    }

    public static ToolTypeDetail generateChainsaw() {
        return ToolTypeDetail.builder()
                .dailyCharge("1.49")
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(true)
                .build();
    }

    public static ToolTypeDetail generateJackhammer() {
        return ToolTypeDetail.builder()
                .dailyCharge("2.99")
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
    }
}
