package com.henricksoares.jasperreport.controller;

import com.henricksoares.jasperreport.model.ContractResponse;
import com.henricksoares.jasperreport.service.FinancingContract;
import com.henricksoares.jasperreport.service.InsuranceDecorator;
import com.henricksoares.jasperreport.service.JasperService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@Slf4j
public class ContractController {

    private final FinancingContract financingContract;

    @GetMapping("/contract")
    public ResponseEntity<ContractResponse> getContract() throws JRException, IOException {
        log.info("Getting contract");
        ContractResponse contractResponse = new ContractResponse();
        InsuranceDecorator insuranceDecorator = new InsuranceDecorator(financingContract, new HashMap<>());
        contractResponse.setContract(JasperService.byteToBase64(insuranceDecorator.generate()));
        return ResponseEntity.ok().body(contractResponse);
    }

}
