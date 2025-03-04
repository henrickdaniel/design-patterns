package com.henricksoares.jasperreport.service;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FinancingContract implements Report{

    private static final String CONTRACT_JASPER = "classpath:jrxml/financingContract.jasper";

    public byte[] generate() throws JRException, FileNotFoundException {
        Map<String, Object> parameters = new HashMap<>();
        return JasperService.buildPdfAsByteArray(CONTRACT_JASPER, parameters);
    }
}
