package com.henricksoares.jasperreport.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class InsuranceDto {


    private String policNumber;
    private LocalDateTime startDate;
    private LocalDate endDate;
    private TypeOfInsurance typeOfInsurance;
    private BigDecimal claimPayment;
    private BigDecimal premiumAmount;

}
