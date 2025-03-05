package com.henricksoares.jasperreport.controller;

import com.henricksoares.jasperreport.model.ContractResponse;
import com.henricksoares.jasperreport.service.ContratService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
public class ContractController {

    private final ContratService contratService;

    @GetMapping("/contract")
    public ResponseEntity<ContractResponse> getContract() throws JRException, IOException {
        log.info("Getting contract");
        ContractResponse contractResponse = new ContractResponse();
        contractResponse.setContract(contratService.generate());
        return ResponseEntity.ok().body(contractResponse);
    }

}
