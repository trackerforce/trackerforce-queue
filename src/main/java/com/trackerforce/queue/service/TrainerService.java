package com.trackerforce.queue.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class TrainerService {

	private RestTemplate restTemplate = new RestTemplate();

//	@Value("${external.api_trainer.url}")
	private String serviceUrl;
	
	public void train(HttpServletRequest request, ProcedureRequest procedureRequest) {
		var headers = new HttpHeaders();
		
		try {
			restTemplate.exchange(
					serviceUrl + "/train/v1/", 
					HttpMethod.POST, 
					new HttpEntity<>(procedureRequest, headers),
					Object.class);

		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getRawStatusCode(), e.getMessage(), e);
		}
	}

}
