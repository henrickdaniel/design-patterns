package com.henricksoares.jasperreport.service;

import com.henricksoares.jasperreport.model.ProposalDto;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface Report {

    public byte[] generate(ProposalDto proposalDto) throws JRException, IOException;

}
