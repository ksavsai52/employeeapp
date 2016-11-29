package com.employee.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteApi {

	private RestTemplate restTemplate;

	public RemoteApi() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public HttpHeaders getHttpHeaders() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		return requestHeaders;
	}

	public HttpEntity<?> getHttpEntity() {
		return new HttpEntity(getHttpHeaders());
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

}
