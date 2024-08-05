package com.trackerforce.queue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public record ProcedureRequest(
	@JsonProperty("context_id")
	String contextId,
	@JsonProperty("tenant_id")
	String tenantId,
	String id,
	String name,
	String resolution,
	List<Task> tasks,
	Hook hook) implements Serializable {

	public static ProcedureRequest createWithHook(ProcedureRequest procedureRequest, Hook hook) {
		return new ProcedureRequest(
				procedureRequest.contextId(),
				procedureRequest.tenantId(),
				procedureRequest.id(),
				procedureRequest.name(),
				procedureRequest.resolution(),
				procedureRequest.tasks(),
				hook);
	}

	public static ProcedureRequest createForSubmission(ProcedureRequest procedureRequest,
													   String contextId, String tenantId) {
		return new ProcedureRequest(
				contextId,
				tenantId,
				procedureRequest.id(),
				procedureRequest.name(),
				procedureRequest.resolution(),
				procedureRequest.tasks(),
				procedureRequest.hook());
	}

}