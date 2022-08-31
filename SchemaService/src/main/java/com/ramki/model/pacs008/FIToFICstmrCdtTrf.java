package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FIToFICstmrCdtTrf {
	
	@JsonProperty("CdtTrfTxInf")
    private CdtTrfTxInf cdtTrfTxInf;

    public CdtTrfTxInf getCdtTrfTxInf() {
        return cdtTrfTxInf;
    }

    public void setCdtTrfTxInf(CdtTrfTxInf cdtTrfTxInf) {
        this.cdtTrfTxInf = cdtTrfTxInf;
    }
}
