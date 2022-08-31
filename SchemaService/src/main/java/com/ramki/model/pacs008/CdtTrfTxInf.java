package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CdtTrfTxInf {
	@JsonProperty("Cdtr")
	private Cdtr cdtr;
	@JsonProperty("PmtTpInf")
	private PmtTpInf pmtTpInf;
	@JsonProperty("DbtrAcct")
	private DbtrAcct dbtrAcct;
	@JsonProperty("IntrBkSttlmAmt")
	private Integer intrBkSttlmAmt;
	@JsonProperty("CdtrAcct")
	private CdtrAcct cdtrAcct;
	@JsonProperty("PmtId")
	private PmtId pmtId;
	@JsonProperty("Dbtr")
	private Dbtr dbtr;

	public Cdtr getCdtr() {
		return cdtr;
	}

	public void setCdtr(Cdtr cdtr) {
		this.cdtr = cdtr;
	}

	public PmtTpInf getPmtTpInf() {
		return pmtTpInf;
	}

	public void setPmtTpInf(PmtTpInf pmtTpInf) {
		this.pmtTpInf = pmtTpInf;
	}

	public DbtrAcct getDbtrAcct() {
		return dbtrAcct;
	}

	public void setDbtrAcct(DbtrAcct dbtrAcct) {
		this.dbtrAcct = dbtrAcct;
	}

	public Integer getIntrBkSttlmAmt() {
		return intrBkSttlmAmt;
	}

	public void setIntrBkSttlmAmt(Integer intrBkSttlmAmt) {
		this.intrBkSttlmAmt = intrBkSttlmAmt;
	}

	public CdtrAcct getCdtrAcct() {
		return cdtrAcct;
	}

	public void setCdtrAcct(CdtrAcct cdtrAcct) {
		this.cdtrAcct = cdtrAcct;
	}

	public PmtId getPmtId() {
		return pmtId;
	}

	public void setPmtId(PmtId pmtId) {
		this.pmtId = pmtId;
	}

	public Dbtr getDbtr() {
		return dbtr;
	}

	public void setDbtr(Dbtr dbtr) {
		this.dbtr = dbtr;
	}
}
