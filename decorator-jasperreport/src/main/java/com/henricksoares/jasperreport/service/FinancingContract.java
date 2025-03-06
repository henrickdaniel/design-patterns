package com.henricksoares.jasperreport.service;

import com.henricksoares.jasperreport.model.ProposalDto;

import java.util.HashMap;

public class FinancingContract extends BaseDecorator {

    private static final String CONTRACT_JASPER = "classpath:jrxml/financingContract.jasper";

    public FinancingContract(ProposalDto proposalDto) {
        super(null, proposalDto);
        this.JASPER_FILE_PATH = CONTRACT_JASPER;
    }

    public HashMap<String, Object> getParameters(ProposalDto proposalDto){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("proposalNumber", proposalDto.getProposalNumber());
        parameters.put("name", proposalDto.getName());
        return parameters;
    }

    @Override
    public Boolean shouldProcess() {
        return true;
    }
}
