package com.trackerforce.queue.config;

import com.github.switcherapi.client.SwitcherContext;
import com.github.switcherapi.client.SwitcherKey;

public class Features extends SwitcherContext {
	
	@SwitcherKey
	public static final String ASYNC_QUEUE = "ASYNC_QUEUE";
	
	@SwitcherKey
	public static final String ML_SERVICE = "ML_SERVICE";

}
