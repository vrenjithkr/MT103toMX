package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LclInstrm {
	@JsonProperty("Prtry")
    private String prtry;

    public String getPrtry() {
        return prtry;
    }

    public void setPrtry(String prtry) {
        this.prtry = prtry;
    }
}
