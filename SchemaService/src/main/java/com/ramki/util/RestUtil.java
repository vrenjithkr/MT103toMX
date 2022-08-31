package com.ramki.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtil {

	@Value("${nest.api.url}")
	private String apiUrl;

	public boolean doPost(String mtFilename, String mxFilename) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
		multipartBodyBuilder.part("teamName", "Hackthon-Ramki");
		ClassPathResource mxResource = new ClassPathResource("/".concat(mxFilename));
		ClassPathResource mtResource = new ClassPathResource("/".concat(mtFilename));
		multipartBodyBuilder.part("pacs008File", mxResource, MediaType.APPLICATION_XML);
		multipartBodyBuilder.part("mt103File", mtResource, MediaType.TEXT_PLAIN);
		MultiValueMap<String, HttpEntity<?>> multipartBody = multipartBodyBuilder.build();
		HttpEntity<MultiValueMap<String, HttpEntity<?>>> httpEntity = new HttpEntity<>(multipartBody, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, httpEntity, String.class);
		System.out.println(responseEntity);
		return true;
	}
}
