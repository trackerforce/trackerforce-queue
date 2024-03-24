package com.trackerforce.queue.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class MLEngineService {

	private final RestTemplate restTemplate = new RestTemplate();

	public void trainProcedure(String serviceUrl, String tenantId, ProcedureRequest procedureRequest) {
		restTemplate.exchange(String.format("%s%s%s", serviceUrl, "/train/v1/", tenantId), HttpMethod.POST,
				new HttpEntity<>(procedureRequest, new HttpHeaders()), Object.class);
	}

}
