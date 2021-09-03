package com.trackerforce.queue.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trackerforce.queue.engine.producers.ProcedureProducerService;
import com.trackerforce.queue.model.ProcedureRequest;

@CrossOrigin(methods = { RequestMethod.POST })
@RestController
@RequestMapping(value = "/queue/session")
public class SessionQueueController {
	
	private final String TENANT_HEADER = "X-Tenant";

	private final ProcedureProducerService procedureService;

	public SessionQueueController(ProcedureProducerService procedureService) {
		this.procedureService = procedureService;
	}

	@PostMapping(value = "/v1/procedure/submit/{tenantId}/{contextId}")
	public ResponseEntity<?> enqueueSubmission(HttpServletRequest request,
			@PathVariable(value = "tenantId") String tenantId,
			@PathVariable(value = "contextId") String contextId,
			@RequestBody ProcedureRequest procedureRequest) {
		procedureRequest.setContextId(contextId);
		procedureRequest.setTenantId(request.getHeader(TENANT_HEADER));
		
		procedureService.submitProcedure(procedureRequest);
		return ResponseEntity.ok().body(procedureRequest);
	}
}
