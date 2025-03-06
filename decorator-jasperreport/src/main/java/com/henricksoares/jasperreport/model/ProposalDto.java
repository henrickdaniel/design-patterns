package com.henricksoares.jasperreport.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class ProposalDto {

    private String proposalNumber;
    private String name;
    private List<InsuranceDto> insuranceDtos;

}
