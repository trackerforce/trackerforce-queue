package com.trackerforce.queue.model;

import java.util.List;

public class Task {

	private String id;

	private boolean learn;

	private Object response;

	private List<TaskOption> options;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isLearn() {
		return learn;
	}

	public void setLearn(boolean learn) {
		this.learn = learn;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public List<TaskOption> getOptions() {
		return options;
	}

	public void setOptions(List<TaskOption> options) {
		this.options = options;
	}

}
