package com.interview.toolrental.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tool {
    private String toolCode;
    private String toolType;
    private String brand;
}
