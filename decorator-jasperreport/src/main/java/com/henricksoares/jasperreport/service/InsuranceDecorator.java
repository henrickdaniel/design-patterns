package com.henricksoares.jasperreport.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InsuranceDecorator implements Report{

    @Autowired
    private JasperService jasperService;
    private Report baseReport;
    private Map<String, Object> insuranceParameters;
    private static final String INSURANCE_JASPER = "classpath:jrxml/insuranceContract.jasper";

    public InsuranceDecorator(Report baseReport, Map<String, Object> insuranceParameters) {
        this.baseReport = baseReport;
        this.insuranceParameters = insuranceParameters;
    }

    @Override
    public byte[] generate() throws JRException, IOException {
        byte[] basePdfBytes = baseReport.generate();
        return JasperService.mergePdfs(basePdfBytes, jasperService.buildPdfAsByteArray(INSURANCE_JASPER, new HashMap<>()));
    }


}
