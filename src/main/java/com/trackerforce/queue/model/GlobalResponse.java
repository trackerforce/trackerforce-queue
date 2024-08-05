package com.trackerforce.queue.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public record GlobalResponse(
		String key,
		String description,
		Map<String, Object> attributes) {

	public String getValue(String attributeKey) {
		return attributes.containsKey(attributeKey) ? attributes.get(attributeKey).toString() : StringUtils.EMPTY;
	}
}
