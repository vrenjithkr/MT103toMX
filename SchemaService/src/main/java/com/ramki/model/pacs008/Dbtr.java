package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dbtr {

	@JsonProperty("Nm")
	private String nm;
	@JsonProperty("PstlAdr")
	private PstlAdr pstlAdr;

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
