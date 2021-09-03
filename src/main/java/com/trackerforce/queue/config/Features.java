package com.trackerforce.queue.config;

import com.github.switcherapi.client.SwitcherContext;
import com.github.switcherapi.client.configuration.SwitcherKey;

public class Features extends SwitcherContext {
	
	@SwitcherKey
	public static final String ASYNC_QUEUE = "ASYNC_QUEUE";

}
