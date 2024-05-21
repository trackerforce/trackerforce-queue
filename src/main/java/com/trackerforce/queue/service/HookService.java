package com.trackerforce.queue.service;

import com.trackerforce.queue.model.ProcedureRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class HookService {

	private final RestTemplate restTemplate = new RestTemplate();

	protected final JwtTokenService jwtTokenService;

	public HookService(JwtTokenService jwtTokenService) {
		this.jwtTokenService = jwtTokenService;
	}

	public void executeHook(ProcedureRequest procedureRequest) {
		var headers = new HttpHeaders();
		var url = procedureRequest.getHook().getResolverUri();

		try {
			restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(procedureRequest, headers), Object.class);
		} catch (Exception e) {
			log.error(String.format("Something went wrong [Hook]: %s", procedureRequest));
		}
	}

}
