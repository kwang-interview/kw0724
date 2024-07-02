package com.interview.toolrental.util;

import com.interview.toolrental.model.Tool;

public class ToolFactory {
    public static Tool generateCHNS() {
        return Tool.builder()
                .toolCode("CHNS")
                .toolType("Chainsaw")
                .brand("Stihl")
                .build();
    }

    public static Tool generateLADW() {
        return Tool.builder()
                .toolCode("LADW")
                .toolType("Ladder")
                .brand("Werner")
                .build();
    }

    public static Tool generateJAKD() {
        return Tool.builder()
                .toolCode("JAKD")
                .toolType("Jackhammer")
                .brand("DeWalt")
                .build();
    }

    public static Tool generateJAKR() {
        return Tool.builder()
                .toolCode("JAKR")
                .toolType("Jackhammer")
                .brand("Ridgid")
                .build();
    }
}
