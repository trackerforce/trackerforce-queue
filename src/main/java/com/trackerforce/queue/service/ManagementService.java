package com.trackerforce.queue.service;

import com.trackerforce.queue.model.GlobalResponse;
import com.trackerforce.queue.model.ProcedureResponse;
import com.trackerforce.queue.type.RequestHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ManagementService {
	
	private final RestTemplate restTemplate = new RestTemplate();

	private final JwtTokenService jwtTokenService;

	private final String serviceUrl;

	public ManagementService(JwtTokenService jwtTokenService,
							 @Value("${service.management.url}/management/") String serviceUrl) {
		this.jwtTokenService = jwtTokenService;
		this.serviceUrl = serviceUrl;
	}

	private HttpHeaders generateHeader(String tenantId) {
		var token = jwtTokenService.generateToken("trackerforce-queue", tenantId);

		var headers = new HttpHeaders();
		headers.add(RequestHeader.AUTHORIZATION.toString(), RequestHeader.BEARER.toString() + token);
		headers.add(RequestHeader.TENANT_HEADER.toString(), tenantId);
		return headers;
	}

	public ProcedureResponse findProcedure(String tenantId, String procedureId) {
		var headers = generateHeader(tenantId);

		try {
			var url = String.format("%s%s%s", serviceUrl, "procedure/v1/", procedureId);
			var response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers),
					ProcedureResponse.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
		}
	}
	
	public GlobalResponse findMLServiceUrl(String tenantId) {
		var headers = generateHeader(tenantId);

		try {
			var url = String.format("%s%s", serviceUrl, "global/v1/ML_SERVICE");
			var response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers),
					GlobalResponse.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
		}
	}

}
