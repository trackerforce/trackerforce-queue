package com.trackerforce.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class ProcedureService {
	
	@Autowired
	private ManagementService managementService;
	
	public void submitProcedure(ProcedureRequest procedureRequest) {
		var procedureResponse = managementService.findProcedure(procedureRequest.getTenantId(),
				procedureRequest.getId());
		
		procedureRequest.setHook(procedureResponse.getHook());
	}

}
