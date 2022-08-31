package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Othr_ {
	
	@JsonProperty("Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
