package com.trackerforce.queue.type;

public enum RequestHeader {
	
	INTERNAL_ORIGIN("X-Origin-Auth"),
	TENANT_HEADER("X-Tenant"),
	AUTHORIZATION("Authorization"),
	BEARER("Bearer ");
	
	private String name;
	
	private RequestHeader(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
