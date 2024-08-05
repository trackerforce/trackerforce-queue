package com.trackerforce.queue.controllers;

import com.trackerforce.queue.engine.producers.ProcedureProducerService;
import com.trackerforce.queue.model.ProcedureRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(methods = { RequestMethod.POST })
@RestController
@RequestMapping(value = "/queue/session/v1")
public class SessionQueueController {

	private final ProcedureProducerService procedureService;

	public SessionQueueController(ProcedureProducerService procedureService) {
		this.procedureService = procedureService;
	}

	@PostMapping(value = "/procedure/submit/{tenantId}/{contextId}")
	public ResponseEntity<ProcedureRequest> enqueueSubmission(HttpServletRequest request,
											   @PathVariable(value = "tenantId") String tenantId,
											   @PathVariable(value = "contextId") String contextId,
											   @RequestBody ProcedureRequest procedureRequest) {
		var procedureRequestSubmit = ProcedureRequest.createForSubmission(procedureRequest, contextId, tenantId);
		procedureService.submitProcedure(procedureRequestSubmit);
		return ResponseEntity.ok().body(procedureRequestSubmit);
	}
	
	@PostMapping(value = "/procedure/next/{tenantId}/{contextId}")
	public ResponseEntity<ProcedureRequest> enqueueNext(HttpServletRequest request,
			@PathVariable(value = "tenantId") String tenantId,
			@PathVariable(value = "contextId") String contextId,
			@RequestBody ProcedureRequest procedureRequest) {
		var procedureRequestSubmit = ProcedureRequest.createForSubmission(procedureRequest, contextId, tenantId);
		procedureService.nextProcedure(procedureRequestSubmit);
		return ResponseEntity.ok().body(procedureRequestSubmit);
	}
}
