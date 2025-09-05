package com.trackerforce.queue.service;

import static com.switcherapi.client.SwitcherContext.getSwitcher;
import static com.trackerforce.queue.config.Features.ML_SERVICE;

import com.trackerforce.queue.model.Hook;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.trackerforce.queue.model.ProcedureRequest;

import java.util.Objects;

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
		var procedureResponse = managementService.findProcedure(
				procedureRequest.tenantId(),
				procedureRequest.id());

		if (Objects.nonNull(procedureResponse.hook()) &&
				Objects.nonNull(procedureResponse.hook().resolverUri())) {
			hookService.executeHook(ProcedureRequest.createWithHook(
					procedureRequest, procedureResponse.hook()));
		}
	}

	public void nextProcedure(ProcedureRequest procedureRequest) {
		if (!getSwitcher(ML_SERVICE).isItOn()) {
			return;
		}

		var mlService = managementService.findMLServiceUrl(procedureRequest.tenantId());
		var serviceUrl = mlService.getValue("url");

		if (serviceUrl != null && !StringUtils.isBlank(serviceUrl)) {
			mLEngineService.trainProcedure(serviceUrl, procedureRequest.tenantId(), procedureRequest);
		}
	}

}
