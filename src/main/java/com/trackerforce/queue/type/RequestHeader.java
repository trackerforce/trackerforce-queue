package com.trackerforce.queue.type;

public enum RequestHeader {

	TENANT_HEADER("X-Tenant"),
	AUTHORIZATION("Authorization"),
	BEARER("Bearer ");
	
	private final String name;
	
	RequestHeader(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
