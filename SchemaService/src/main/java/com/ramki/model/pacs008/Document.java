package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {

	@JsonProperty("FIToFICstmrCdtTrf")
	private FIToFICstmrCdtTrf fIToFICstmrCdtTrf;

	public FIToFICstmrCdtTrf getfIToFICstmrCdtTrf() {
		return fIToFICstmrCdtTrf;
	}

	public void setfIToFICstmrCdtTrf(FIToFICstmrCdtTrf fIToFICstmrCdtTrf) {
		this.fIToFICstmrCdtTrf = fIToFICstmrCdtTrf;
	}

}
