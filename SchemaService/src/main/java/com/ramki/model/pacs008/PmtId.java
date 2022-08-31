package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PmtId {
	@JsonProperty("InstrId")
    private String instrId;

    public String getInstrId() {
        return instrId;
    }

    public void setInstrId(String instrId) {
        this.instrId = instrId;
    }
}
