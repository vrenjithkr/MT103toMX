package com.ramki.hackathon.model.pacs008;

public class CdtTrfTxInf {
    private Cdtr cdtr;
    private PmtTpInf pmtTpInf;
    private DbtrAcct dbtrAcct;
    private Integer intrBkSttlmAmt;
    private CdtrAcct cdtrAcct;
    private PmtId pmtId;
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
