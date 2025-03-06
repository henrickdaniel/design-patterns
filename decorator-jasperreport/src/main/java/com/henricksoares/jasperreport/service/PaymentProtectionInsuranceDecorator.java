package com.henricksoares.jasperreport.service;


import com.henricksoares.jasperreport.model.InsuranceDto;
import com.henricksoares.jasperreport.model.ProposalDto;
import com.henricksoares.jasperreport.model.TypeOfInsurance;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
public class PaymentProtectionInsuranceDecorator extends BaseDecorator{

    private static final String INSURANCE_JASPER_FILE_PATH = "classpath:jrxml/paymentProtectionInsurance.jasper";

    public PaymentProtectionInsuranceDecorator(BaseDecorator baseReport) {
        super(baseReport, baseReport.proposalDto);
        this.JASPER_FILE_PATH = INSURANCE_JASPER_FILE_PATH;
    }

    @Override
    public HashMap<String, Object> getParameters(ProposalDto proposalDto){
        HashMap<String, Object> parameters = this.baseReport.getParameters(proposalDto);
        if(shouldProcess()){
            parameters.put("insuranceClaimPayment", findPaymentProtectionInsurance().get().getClaimPayment());
        }
        return parameters;
    }

    @Override
    public Boolean shouldProcess() {
        return findPaymentProtectionInsurance().isPresent();
    }

    private Optional<InsuranceDto> findPaymentProtectionInsurance(){
        if(this.proposalDto.getInsuranceDtos() == null || this.proposalDto.getInsuranceDtos().isEmpty()){
            return Optional.empty();
        }
        return this.proposalDto.getInsuranceDtos().stream().filter(insuranceDto -> insuranceDto != null && insuranceDto.getTypeOfInsurance() != null && insuranceDto.getTypeOfInsurance().equals(TypeOfInsurance.PAYMENT_PROTECTION_INSURANCE)).findFirst();

    }
}
