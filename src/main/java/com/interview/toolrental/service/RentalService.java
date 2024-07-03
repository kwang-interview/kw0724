package com.interview.toolrental.service;

import com.interview.toolrental.model.RentalAgreement;
import com.interview.toolrental.model.Tool;
import com.interview.toolrental.model.ToolTypeDetail;
import com.interview.toolrental.util.DateUtil;
import com.interview.toolrental.util.ToolFactory;
import com.interview.toolrental.util.ToolTypeDetailFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class RentalService implements IRentalService {
    private final IDateService dateService;

    public RentalService(IDateService dateService) {
        this.dateService = dateService;
    }

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
            throw new Exception(String.format("Discount percent [%d] is not in the range 0-100", discountPercent));
        }

        final var tool = getTool(toolCode);
        final var toolTypeDetail = getToolTypeDetail(tool);

        var rentalAgreement = RentalAgreement.builder()
                .toolCode(tool.getToolCode())
                .toolType(tool.getToolType())
                .toolBrand(tool.getBrand())
                .rentalDays(rentalDays)
                .checkoutDate(checkoutDate)
                .dueDate(checkoutDate.plusDays(rentalDays))
                .dailyRentalCharge(toolTypeDetail.getDailyCharge())
                .discountPercent(discountPercent)
                .build();

        final int chargeDays = calculateChargeDays(checkoutDate, rentalAgreement.getDueDate(), toolTypeDetail);
        rentalAgreement.setChargeDays(chargeDays);

        final String subTotal = calculateSubtotal(toolTypeDetail.getDailyCharge(), chargeDays);
        rentalAgreement.setPrediscountCharge(subTotal);

        final String discountTotal = calculateDiscountTotal(subTotal, discountPercent);
        rentalAgreement.setDiscountAmount(discountTotal);

        final String total = new BigDecimal(subTotal).subtract(new BigDecimal(discountTotal)).toPlainString();
        rentalAgreement.setFinalCharge(total);

        return rentalAgreement;
    }

    private Tool getTool(String toolCode) throws Exception {
        return switch (toolCode) {
            case "CHNS": yield ToolFactory.generateCHNS();
            case "LADW": yield ToolFactory.generateLADW();
            case "JAKD": yield ToolFactory.generateJAKD();
            case "JAKR": yield ToolFactory.generateJAKR();
            default: throw new Exception("Unsupported tool code");
        };
    }

    private ToolTypeDetail getToolTypeDetail(Tool tool) throws Exception {
        return switch (tool.getToolType().toLowerCase()) {
            case "ladder": yield ToolTypeDetailFactory.generateLadder();
            case "chainsaw": yield ToolTypeDetailFactory.generateChainsaw();
            case "jackhammer": yield ToolTypeDetailFactory.generateJackhammer();
            default: throw new Exception("Unsupported tool type");
        };
    }

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, ToolTypeDetail toolType) {
        var days = (int) ChronoUnit.DAYS.between(checkoutDate, dueDate);
        var weekends = DateUtil.calculateWeekends(checkoutDate, dueDate);

        var excludeHolidayDays = toolType.isHolidayCharge() ? 0 : calculateHolidayDays(checkoutDate, dueDate);
        var chargeWeekdays = toolType.isWeekdayCharge() ? days - weekends - excludeHolidayDays : 0;
        var chargeWeekends = toolType.isWeekendCharge() ? weekends : 0;

        return chargeWeekdays + chargeWeekends;
    }

    private int calculateHolidayDays(LocalDate fromDate, LocalDate toDate) {
        var yearsBetween = toDate.getYear() - fromDate.getYear();
        var holidays = new ArrayList<LocalDate>();
        for (int x = 0; x < yearsBetween + 1; x++) {
            holidays.addAll(dateService.getAllHolidays(fromDate.getYear() + x));
        }

        var validHolidays = 0;
        for (var holiday: holidays) {
            if (fromDate.isBefore(holiday) && toDate.isAfter(holiday)) {
                validHolidays++;
            }
        }

        return validHolidays;
    }

    private String calculateSubtotal(String dailyCharge, int chargeDays) {
        var subtotal = new BigDecimal(dailyCharge).multiply(BigDecimal.valueOf(chargeDays));
        return subtotal.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private String calculateDiscountTotal(String subtotal, int discountPercent) {
        var decimalPercent = BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100));
        var discountTotal = new BigDecimal(subtotal).multiply(decimalPercent);
        return discountTotal.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
