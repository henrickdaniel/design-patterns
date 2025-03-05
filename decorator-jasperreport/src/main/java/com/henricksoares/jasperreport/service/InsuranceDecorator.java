package com.henricksoares.jasperreport.service;


import com.henricksoares.jasperreport.model.ProposalDto;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
@Slf4j
public class InsuranceDecorator extends BaseDecorator{

    private static final String INSURANCE_JASPER_FILE_PATH = "classpath:jrxml/insuranceContract.jasper";

    public InsuranceDecorator(BaseDecorator baseReport) {
        super(baseReport, baseReport.proposalDto);
        this.JASPER_FILE_PATH = INSURANCE_JASPER_FILE_PATH;
    }

    @Override
    public HashMap<String, Object> getParameters(ProposalDto proposalDto){
        HashMap<String, Object> parameters = this.baseReport.getParameters(proposalDto);
        parameters.put("insuranceClaimPayment", BigDecimal.valueOf(200000D));
        return parameters;
    }
}
