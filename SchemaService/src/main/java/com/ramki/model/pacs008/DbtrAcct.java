package com.ramki.model.pacs008;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DbtrAcct {
	@JsonProperty("Id")
    private Id id;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
