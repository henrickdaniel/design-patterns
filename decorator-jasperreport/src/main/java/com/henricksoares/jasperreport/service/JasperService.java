package com.henricksoares.jasperreport.service;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Base64;
import java.util.Map;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class JasperService {

    public static JasperPrint getJasperPrint(String jasperCompiledFilePath, Map<String, Object> parameters) throws FileNotFoundException, JRException {
        File jasper = ResourceUtils.getFile(jasperCompiledFilePath);
        return JasperFillManager.fillReport(jasper.getAbsolutePath(), parameters, new JREmptyDataSource());
    }

    public static byte[] buildPdfAsByteArray(String jasperCompiledFilePath, Map<String, Object> parameters) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getJasperPrint(jasperCompiledFilePath, parameters));
    }

    public static String byteToBase64(byte[] bytes) throws JRException, FileNotFoundException {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] mergePdfs(byte[] pdf1, byte[] pdf2) throws IOException {
        PDFMergerUtility merger = new PDFMergerUtility();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (InputStream inputStream1 = new ByteArrayInputStream(pdf1);
             InputStream inputStream2 = new ByteArrayInputStream(pdf2)) {

            merger.setDestinationStream(outputStream);
            merger.addSource(inputStream1);
            merger.addSource(inputStream2);
            merger.mergeDocuments(null);

            return outputStream.toByteArray();
        }
    }

}
