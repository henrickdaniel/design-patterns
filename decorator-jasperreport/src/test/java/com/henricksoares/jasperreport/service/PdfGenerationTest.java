package com.henricksoares.jasperreport.service;

import com.github.javafaker.Faker;
import com.henricksoares.jasperreport.model.InsuranceDto;
import com.henricksoares.jasperreport.model.ProposalDto;
import com.henricksoares.jasperreport.model.TypeOfInsurance;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PdfGenerationTest {

    @Test
    void testGenerateMainPdfOnly() throws JRException, IOException {
        ProposalDto proposalDto = getProposalDto();
        BaseDecorator baseDecorator = spy(new FinancingContract(proposalDto));
        AutoInsuranceDecorator autoInsuranceDecorator = spy(new AutoInsuranceDecorator(baseDecorator));
        PaymentProtectionInsuranceDecorator paymentProtectionInsuranceDecorator = spy(new PaymentProtectionInsuranceDecorator(autoInsuranceDecorator));

        byte[] mainPdf = paymentProtectionInsuranceDecorator.generate();
        assertNotNull(mainPdf);
        verify(baseDecorator, times(1)).generate();
    }

    @Test
    void testGenerateMainAndAutoInsurancePdf() throws JRException, IOException {
        ProposalDto proposalDto = getProposalDto(TypeOfInsurance.AUTO_INSURANCE);
        BaseDecorator baseDecorator = spy(new FinancingContract(proposalDto));
        AutoInsuranceDecorator autoInsuranceDecorator = spy(new AutoInsuranceDecorator(baseDecorator));
        PaymentProtectionInsuranceDecorator paymentProtectionInsuranceDecorator = spy(new PaymentProtectionInsuranceDecorator(autoInsuranceDecorator));

        byte[] pdf = paymentProtectionInsuranceDecorator.generate();
        assertNotNull(pdf);
        verify(autoInsuranceDecorator, times(1)).generate();
        verify(baseDecorator, times(1)).generate();
    }

    @Test
    void testGenerateMainAndPaymentProtectionPdf() throws JRException, IOException {
        ProposalDto proposalDto = getProposalDto(TypeOfInsurance.PAYMENT_PROTECTION_INSURANCE);
        BaseDecorator baseDecorator = spy(new FinancingContract(proposalDto));
        AutoInsuranceDecorator autoInsuranceDecorator = spy(new AutoInsuranceDecorator(baseDecorator));
        PaymentProtectionInsuranceDecorator paymentProtectionInsuranceDecorator = spy(new PaymentProtectionInsuranceDecorator(autoInsuranceDecorator));

        byte[] pdf = paymentProtectionInsuranceDecorator.generate();
        assertNotNull(pdf);
        verify(paymentProtectionInsuranceDecorator, times(1)).generate();
        verify(autoInsuranceDecorator, times(1)).generate();
        verify(baseDecorator, times(1)).generate();
    }

    private ProposalDto getProposalDto(){
        return ProposalDto
                .builder()
                .proposalNumber(Faker.instance().number().digits(10))
                .name(Faker.instance().starTrek().character())
                .build();
    }

    private ProposalDto getProposalDto(TypeOfInsurance typeOfInsurance){
        ProposalDto proposalDto = getProposalDto();
        proposalDto.setInsuranceDtos(Arrays.asList(new InsuranceDto[]{
                getInsuranceDto(typeOfInsurance)
        }));
        return proposalDto;
    }

    private InsuranceDto getInsuranceDto(TypeOfInsurance typeOfInsurance){
        return InsuranceDto
                .builder()
                .typeOfInsurance(typeOfInsurance)
                .startDate(LocalDateTime.now())
                .claimPayment(BigDecimal.valueOf(10D))
                .build();
    }
}