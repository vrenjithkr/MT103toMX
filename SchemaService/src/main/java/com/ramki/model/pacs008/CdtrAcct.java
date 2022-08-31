package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CdtrAcct {
	@JsonProperty("Id_")
    private Id_ id;

    public Id_ getId() {
        return id;
    }

    public void setId(Id_ id) {
        this.id = id;
    }
}
