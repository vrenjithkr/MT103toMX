package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PstlAdr {
	
	@JsonProperty("AdrLine")
    private String adrLine;

    public String getAdrLine() {
        return adrLine;
    }

    public void setAdrLine(String adrLine) {
        this.adrLine = adrLine;
    }
}
