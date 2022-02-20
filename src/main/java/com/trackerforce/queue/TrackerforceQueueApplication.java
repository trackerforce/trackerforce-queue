package com.trackerforce.queue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.trackerforce.queue.config.Features;

@SpringBootApplication
public class TrackerforceQueueApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TrackerforceQueueApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Features.checkSwitchers();
		Features.watchSnapshot();
	}
}
