package com.henricksoares.jasperreport.service;

import com.github.javafaker.Faker;
import com.henricksoares.jasperreport.model.InsuranceDto;
import com.henricksoares.jasperreport.model.ProposalDto;
import com.henricksoares.jasperreport.model.TypeOfInsurance;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ContratService {

    public String generate() throws JRException, IOException {

        ProposalDto proposalDto = getProposal();

        FinancingContract financingContract = new FinancingContract(proposalDto);
        PaymentProtectionInsuranceDecorator paymentProtectionInsuranceDecorator = new PaymentProtectionInsuranceDecorator(financingContract);
        AutoInsuranceDecorator autoInsuranceDecorator = new AutoInsuranceDecorator(paymentProtectionInsuranceDecorator);
        return autoInsuranceDecorator.generateAsString();
    }

    public ProposalDto getProposal(){
        return ProposalDto
                .builder()
                .proposalNumber(Faker.instance().number().digits(10))
                .name(Faker.instance().starTrek().character())
                .insuranceDtos(Arrays.asList(new InsuranceDto[]{
                        InsuranceDto.builder()
                                .claimPayment(BigDecimal.valueOf(Faker.instance().number().randomDouble(2, 10000,100000)))
                                .startDate(LocalDateTime.now())
                                .typeOfInsurance(TypeOfInsurance.AUTO_INSURANCE)
                                .build()
                }))
                .build();
    }

}
