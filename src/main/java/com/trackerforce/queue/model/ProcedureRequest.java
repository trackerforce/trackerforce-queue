package com.trackerforce.queue.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ProcedureRequest implements Serializable {

	@JsonProperty("context_id")
	private String contextId;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	private String id;

	private String name;

	private String resolution;

	private List<Task> tasks;
	
	private Hook hook;

	@Override
	public String toString() {
		return "ProcedureRequest [contextId=" + contextId + ", tenantId=" + tenantId + ", id=" + id + ", name=" + name
				+ "]";
	}

}
