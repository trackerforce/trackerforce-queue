package com.trackerforce.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class ProcedureService {

	@Autowired
	private ManagementService managementService;

	@Autowired
	private HookService hookService;

	public void submitProcedure(ProcedureRequest procedureRequest) {
		var procedureResponse = managementService.findProcedure(procedureRequest.getTenantId(),
				procedureRequest.getId());

		if (procedureResponse.getHook() != null) {
			procedureRequest.setHook(procedureResponse.getHook());
			hookService.executeHook(procedureRequest);
		}
	}

}
