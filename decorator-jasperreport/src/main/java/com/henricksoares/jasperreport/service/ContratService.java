package com.henricksoares.jasperreport.service;

import com.henricksoares.jasperreport.model.ContractResponse;
import com.henricksoares.jasperreport.model.ProposalDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ContratService {

    public String generate() throws JRException, IOException {
        ProposalDto proposalDto = new ProposalDto();
        proposalDto.setProposalNumber("123456");
        proposalDto.setName("Henrick Daniel Soares");
        FinancingContract financingContract = new FinancingContract(proposalDto);
        InsuranceDecorator insuranceDecorator = new InsuranceDecorator(financingContract);

        return insuranceDecorator.generateAsString(proposalDto);
    }

}
