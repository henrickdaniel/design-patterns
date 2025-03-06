package com.henricksoares.jasperreport.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface Report {

    public byte[] generate() throws JRException, IOException;

}
