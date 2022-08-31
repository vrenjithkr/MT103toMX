package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PmtTpInf {
	@JsonProperty("LclInstrm")
    private LclInstrm lclInstrm;

    public LclInstrm getLclInstrm() {
        return lclInstrm;
    }

    public void setLclInstrm(LclInstrm lclInstrm) {
        this.lclInstrm = lclInstrm;
    }
}
