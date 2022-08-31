package com.ramki.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.ramki.model.pacs008.CdtTrfTxInf;
import com.ramki.model.pacs008.Cdtr;
import com.ramki.model.pacs008.CdtrAcct;
import com.ramki.model.pacs008.Dbtr;
import com.ramki.model.pacs008.DbtrAcct;
import com.ramki.model.pacs008.Document;
import com.ramki.model.pacs008.FIToFICstmrCdtTrf;
import com.ramki.model.pacs008.Id;
import com.ramki.model.pacs008.LclInstrm;
import com.ramki.model.pacs008.Othr;
import com.ramki.model.pacs008.PmtId;
import com.ramki.model.pacs008.PmtTpInf;
import com.ramki.model.pacs008.PstlAdr;

@Component
public class MXConverter {

	static final Logger log = LoggerFactory.getLogger(MXConverter.class);

	public Document convert(MT103 mtMessage) {
		Document mxDocument = new Document();
		FIToFICstmrCdtTrf fiToFICstmrCdtTrf = new FIToFICstmrCdtTrf();
		CdtTrfTxInf cdtTrfTxInf = new CdtTrfTxInf();

		PmtId pmtId = new PmtId();
		pmtId.setInstrId(null != mtMessage.getField20() ? mtMessage.getField20().getValue() : "");
		cdtTrfTxInf.setPmtId(pmtId);

		PmtTpInf pmtTpInf = new PmtTpInf();
		LclInstrm lclInstrm = new LclInstrm();
		lclInstrm.setPrtry(null != mtMessage.getField23B() ? mtMessage.getField23B().getValue() : "");
		pmtTpInf.setLclInstrm(lclInstrm);
		cdtTrfTxInf.setPmtTpInf(pmtTpInf);

		cdtTrfTxInf.setIntrBkSttlmAmt(
				null != mtMessage.getField33B().amount() ? Integer.parseInt(mtMessage.getField33B().amount().toString())
						: 0);

		if (null != mtMessage.getField50K()) {
			Dbtr dbtr = new Dbtr();
			dbtr.setNm(mtMessage.getField50K().getNameAndAddressLine1());
			PstlAdr pstlAdr = new PstlAdr();
			pstlAdr.setAdrLine(mtMessage.getField50K().getNameAndAddressLine2()
					.concat("\n" + mtMessage.getField50K().getNameAndAddressLine3()));
			dbtr.setPstlAdr(pstlAdr);
			cdtTrfTxInf.setDbtr(dbtr);

			DbtrAcct dbtrAcct = new DbtrAcct();
			Id id = new Id();
			Othr othr = new Othr();
			othr.setId(mtMessage.getField50K().getLines(0).get(0));
			id.setOthr(othr);
			dbtrAcct.setId(id);
			cdtTrfTxInf.setDbtrAcct(dbtrAcct);
		}

		if (null != mtMessage.getField59()) {
			Cdtr cdtr = new Cdtr();
			cdtr.setNm(mtMessage.getField59().getNameAndAddress());
			cdtTrfTxInf.setCdtr(cdtr);

			CdtrAcct cdtrAcct = new CdtrAcct();
			Id cdId = new Id();
			Othr cdOthr = new Othr();
			cdOthr.setId("/" + mtMessage.getField59().getAccount());
			cdId.setOthr(cdOthr);
			cdtrAcct.setId(cdId);
			cdtTrfTxInf.setCdtrAcct(cdtrAcct);
		}

		fiToFICstmrCdtTrf.setCdtTrfTxInf(cdtTrfTxInf);
		mxDocument.setfIToFICstmrCdtTrf(fiToFICstmrCdtTrf);

		XmlMapper xmlMapper = new XmlMapper();

		String mxdata = null;
		try {
			mxdata = xmlMapper.writeValueAsString(mxDocument);
		} catch (JsonProcessingException e) {
			log.error("Error in MXConverter at method convert. " + e.getMessage());
		}
		return mxDocument;

	}

	public String convert(Document mxMessage) {
		String mxdata = null;
		XmlMapper xmlMapper = new XmlMapper();
		try {
			mxdata = xmlMapper.writeValueAsString(mxMessage);
		} catch (JsonProcessingException e) {
			log.error("Error in MXConverter at method convert. " + e.getMessage());
		} // TODO Auto-generated method stub
		return mxdata;
	}

}
