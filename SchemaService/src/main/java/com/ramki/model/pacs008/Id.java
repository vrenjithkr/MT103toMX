package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Id {
	@JsonProperty("Othr")
	private Othr othr;

	public Othr getOthr() {
		return othr;
	}

	public void setOthr(Othr othr) {
		this.othr = othr;
	}
}
