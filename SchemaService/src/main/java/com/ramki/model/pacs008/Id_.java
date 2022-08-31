package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Id_ {
	
	@JsonProperty("Othr_s")
    private Othr_ othr;

    public Othr_ getOthr() {
        return othr;
    }

    public void setOthr(Othr_ othr) {
        this.othr = othr;
    }
}
