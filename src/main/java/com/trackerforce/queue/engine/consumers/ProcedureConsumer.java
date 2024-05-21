package com.trackerforce.queue.engine.consumers;

import com.trackerforce.queue.model.ProcedureRequest;
import com.trackerforce.queue.service.ProcedureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcedureConsumer {

	private final ProcedureService procedureService;

	public ProcedureConsumer(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}

	@KafkaListener(topics = "procedure_submissions", groupId = "trackerforce_task_group")
	public void consumeProcedureSubmission(ProcedureRequest procedureRequest) {
		log.atInfo().log(() -> "#### -> Consumed message -> %s".formatted(procedureRequest));
		procedureService.submitProcedure(procedureRequest);
	}
	
	@KafkaListener(topics = "procedure_next", groupId = "trackerforce_task_group")
	public void consumeProcedureNext(ProcedureRequest procedureRequest) {
		log.atInfo().log(() -> "#### -> Consumed message -> %s".formatted(procedureRequest));
		procedureService.nextProcedure(procedureRequest);
	}

}
