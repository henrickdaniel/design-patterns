package com.henricksoares.jasperreport.service;

import com.henricksoares.jasperreport.model.ProposalDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
abstract class BaseDecorator implements Report{

    protected BaseDecorator baseReport;
    protected String JASPER_FILE_PATH;
    protected ProposalDto proposalDto;

    public BaseDecorator(BaseDecorator report, ProposalDto proposalDto){
        this.baseReport = report;
        this.proposalDto = proposalDto;
    }

    public byte[] generate(ProposalDto proposalDto) throws JRException, IOException {
        log.info("Generating contract with JASPER_FILE_PATH {}", JASPER_FILE_PATH);
        if(baseReport == null){
            return JasperService.buildPdfAsByteArray(JASPER_FILE_PATH, getParameters(proposalDto));
        }
        byte[] basePdfBytes = baseReport.generate(proposalDto);
        return JasperService.mergePdfs(basePdfBytes, JasperService.buildPdfAsByteArray(JASPER_FILE_PATH, getParameters(proposalDto)));
    }

    public String generateAsString(ProposalDto proposalDto) throws JRException, IOException {
        return JasperService.byteToBase64(generate(proposalDto));
    }

    public abstract HashMap<String, Object> getParameters(ProposalDto proposalDto);

}
