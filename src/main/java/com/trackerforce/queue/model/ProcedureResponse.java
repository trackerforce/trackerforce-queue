package com.trackerforce.queue.model;

import java.util.List;

public record ProcedureResponse(
	String id,
	String name,
	List<Task> tasks,
	Hook hook) {

}
