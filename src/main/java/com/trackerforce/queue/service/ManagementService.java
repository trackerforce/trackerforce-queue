package com.trackerforce.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.trackerforce.queue.model.ProcedureResponse;

@Service
public class ManagementService {

	private static final String AUTHORIZATION = "Authorization";
	private static final String TENANT_HEADER = "X-Tenant";
	private static final String BEARER = "Bearer ";

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	protected JwtTokenService jwtTokenService;

	@Value("${service.management.url}/management/")
	private String serviceUrl;

	public ProcedureResponse findProcedure(String tenantId, String procedureId) {
		var token = jwtTokenService.generateToken("trackerforce-queue", "client");

		var headers = new HttpHeaders();
		headers.add(AUTHORIZATION, BEARER + token);
		headers.add(TENANT_HEADER, tenantId);

		try {
			var url = String.format("%s%s%s", serviceUrl, "procedure/v1/", procedureId);
			var response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers),
					ProcedureResponse.class);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getRawStatusCode(), e.getMessage(), e);
		}
	}

}
