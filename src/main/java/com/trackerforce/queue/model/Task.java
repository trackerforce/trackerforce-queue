package com.trackerforce.queue.model;

import lombok.Data;

import java.util.List;

@Data
public class Task {

	private String id;

	private boolean learn;

	private Object response;

	private List<TaskOption> options;

}
