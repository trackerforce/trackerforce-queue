package com.trackerforce.queue.model;

import lombok.Data;

import java.util.List;

@Data
public class ProcedureResponse {

	private String id;

	private String name;

	private List<Task> tasks;

	private Hook hook;

}
