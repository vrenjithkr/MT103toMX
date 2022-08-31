package com.ramki.model.pacs008;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
	@JsonProperty("Document")
	private Document document;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
}
