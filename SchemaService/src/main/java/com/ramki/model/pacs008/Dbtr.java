package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dbtr {
	@JsonProperty("PstlAdr")
	private PstlAdr pstlAdr;
	@JsonProperty("Nm")
	private String nm;

	public PstlAdr getPstlAdr() {
		return pstlAdr;
	}

	public void setPstlAdr(PstlAdr pstlAdr) {
		this.pstlAdr = pstlAdr;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
}
