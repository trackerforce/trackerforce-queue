package com.trackerforce.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class HookService {

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	protected JwtTokenService jwtTokenService;

	public void executeHook(ProcedureRequest procedureRequest) {
		var headers = new HttpHeaders();
		var url = procedureRequest.getHook().getResolverUri();

		restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(procedureRequest, headers), Object.class);
	}

}
