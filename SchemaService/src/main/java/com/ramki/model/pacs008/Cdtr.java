package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cdtr {
	@JsonProperty("Nm")
    private String nm;

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }
}
