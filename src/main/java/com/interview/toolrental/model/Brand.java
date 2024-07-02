package com.interview.toolrental.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Brand {
    private String dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
}
