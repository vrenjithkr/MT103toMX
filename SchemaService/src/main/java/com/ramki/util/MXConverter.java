package com.ramki.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.ramki.model.pacs008.*;
import com.ramki.service.ValidationService;

@Component
public class MXConverter {

	static final Logger log = LoggerFactory.getLogger(MXConverter.class);
			
	public Document convert(MT103 mtMessage) {
		Document mxDocument = new Document();
		FIToFICstmrCdtTrf fiToFICstmrCdtTrf = new FIToFICstmrCdtTrf();
		CdtTrfTxInf cdtTrfTxInf = new CdtTrfTxInf();
		
		Cdtr cdtr = new Cdtr();
		cdtr.setNm(mtMessage.getField59().getNameAndAddress());
		cdtTrfTxInf.setCdtr(cdtr);
		
		PmtTpInf pmtTpInf = new PmtTpInf();
		LclInstrm lclInstrm = new LclInstrm();
		lclInstrm.setPrtry(mtMessage.getField23B().getValue());
		pmtTpInf.setLclInstrm(lclInstrm);
		cdtTrfTxInf.setPmtTpInf(pmtTpInf);
		
		DbtrAcct dbtrAcct = new DbtrAcct();
		Id id = new Id();
		Othr othr = new Othr();
		othr.setId(mtMessage.getField50K().getLines(0).get(0));
		id.setOthr(othr);
		dbtrAcct.setId(id);
		cdtTrfTxInf.setDbtrAcct(dbtrAcct);
		
		cdtTrfTxInf.setIntrBkSttlmAmt(Integer.parseInt(mtMessage.getField33B().amount().toString()));
		
		CdtrAcct cdtrAcct = new CdtrAcct();
		Id_ cdId = new Id_();
		Othr_ cdOthr = new Othr_() ;
		cdOthr.setId("/"+mtMessage.getField59().getAccount());
		cdId.setOthr(cdOthr);
		cdtrAcct.setId(cdId);
		cdtTrfTxInf.setCdtrAcct(cdtrAcct);
		
		PmtId pmtId = new PmtId();
		pmtId.setInstrId(mtMessage.getField20().getValue());
		cdtTrfTxInf.setPmtId(pmtId);
		
		Dbtr dbtr = new Dbtr();
		dbtr.setNm(mtMessage.getField50K().getNameAndAddressLine1());
		PstlAdr pstlAdr = new PstlAdr();
		pstlAdr.setAdrLine(mtMessage.getField50K().getNameAndAddressLine2().concat("\n"+mtMessage.getField50K().getNameAndAddressLine3()));
		dbtr.setPstlAdr(pstlAdr);
		cdtTrfTxInf.setDbtr(dbtr);


		fiToFICstmrCdtTrf.setCdtTrfTxInf(cdtTrfTxInf);
		mxDocument.setfIToFICstmrCdtTrf(fiToFICstmrCdtTrf);

		XmlMapper xmlMapper = new XmlMapper();

		String mxdata = null;
		try {
			mxdata = xmlMapper.writeValueAsString(mxDocument);
		} catch (JsonProcessingException e) {
			log.error("Error in MXConverter at method convert. "+e.getMessage());
		}
		return mxDocument;

	}

}
