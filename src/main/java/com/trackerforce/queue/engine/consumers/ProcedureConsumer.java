package com.trackerforce.queue.engine.consumers;

import com.trackerforce.queue.model.ProcedureRequest;
import com.trackerforce.queue.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProcedureConsumer {

	private final Logger logger = LoggerFactory.getLogger(ProcedureConsumer.class);

	private final ProcedureService procedureService;

	public ProcedureConsumer(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}

	@KafkaListener(topics = "procedure_submissions", groupId = "trackerforce_task_group")
	public void consumeProcedureSubmission(ProcedureRequest procedureRequest) {
		logger.atInfo().log(() -> "#### -> Consumed message -> %s".formatted(procedureRequest));
		procedureService.submitProcedure(procedureRequest);
	}
	
	@KafkaListener(topics = "procedure_next", groupId = "trackerforce_task_group")
	public void consumeProcedureNext(ProcedureRequest procedureRequest) {
		logger.atInfo().log(() -> "#### -> Consumed message -> %s".formatted(procedureRequest));
		procedureService.nextProcedure(procedureRequest);
	}

}
