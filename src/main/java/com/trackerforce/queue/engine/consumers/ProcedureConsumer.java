package com.trackerforce.queue.engine.consumers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.trackerforce.queue.model.ProcedureRequest;

@Service
public class ProcedureConsumer {

	private final Logger logger = LoggerFactory.getLogger(ProcedureConsumer.class);

	@KafkaListener(topics = "procedure_submissions", groupId = "trackerforce_task_group")
	public void consumeProcedure(ProcedureRequest procedureRequest) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", procedureRequest));
	}

}
