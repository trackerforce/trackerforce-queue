package com.trackerforce.queue.service;

import static com.github.switcherapi.client.SwitcherContext.getSwitcher;
import static com.trackerforce.queue.config.Features.ML_SERVICE;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class ProcedureService {
	
	private final ManagementService managementService;
	private final HookService hookService;
	private final MLEngineService mLEngineService;
	
	public ProcedureService(ManagementService managementService, HookService hookService,
			MLEngineService mLEngineService) {
		this.managementService = managementService;
		this.hookService = hookService;
		this.mLEngineService = mLEngineService;
	}

	public void submitProcedure(ProcedureRequest procedureRequest) {
		var procedureResponse = managementService.findProcedure(procedureRequest.getTenantId(),
				procedureRequest.getId());

		if (procedureResponse.getHook() != null) {
			procedureRequest.setHook(procedureResponse.getHook());
			hookService.executeHook(procedureRequest);
		}
	}

	public void nextProcedure(ProcedureRequest procedureRequest) {
		if (!getSwitcher(ML_SERVICE).isItOn())
			return;

		var mlService = managementService.findMLServiceUrl(procedureRequest.getTenantId());
		var serviceUrl = mlService.getValue("url");

		if (serviceUrl != null && !StringUtils.isBlank(serviceUrl))
			mLEngineService.trainProcedure(serviceUrl, procedureRequest.getTenantId(), procedureRequest);
	}

}
