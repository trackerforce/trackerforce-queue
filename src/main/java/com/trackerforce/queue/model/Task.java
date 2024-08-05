package com.trackerforce.queue.model;

import java.util.List;

public record Task(
	String id,
	boolean learn,
	Object response,
	List<TaskOption> options) {

}
