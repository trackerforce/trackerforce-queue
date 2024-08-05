package com.trackerforce.queue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.trackerforce.queue.config.Features.checkSwitchers;
import static com.trackerforce.queue.config.Features.watchSnapshot;

@SpringBootApplication
@ConfigurationPropertiesScan( basePackages = { "com.trackerforce.queue.config" } )
public class TrackerforceQueueApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TrackerforceQueueApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		checkSwitchers();
		watchSnapshot();
	}
}
