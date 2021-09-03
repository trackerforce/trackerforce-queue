package com.trackerforce.queue.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public Hook getHook() {
		return hook;
	}

	public void setHook(Hook hook) {
		this.hook = hook;
	}

	@Override
	public String toString() {
		return "ProcedureRequest [contextId=" + contextId + ", tenantId=" + tenantId + ", id=" + id + ", name=" + name
				+ "]";
	}

}
