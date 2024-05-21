package com.trackerforce.queue;

import com.trackerforce.queue.controllers.SessionQueueController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

	@Autowired
	SessionQueueController sessionQueueController;

	@Test
	void contextLoads() {
		assertNotNull(sessionQueueController);
	}

}
