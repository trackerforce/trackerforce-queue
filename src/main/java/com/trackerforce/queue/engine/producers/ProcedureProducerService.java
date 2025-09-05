package com.trackerforce.queue.engine.producers;

import com.trackerforce.queue.model.ProcedureRequest;
import com.trackerforce.queue.service.ProcedureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.switcherapi.client.SwitcherContext.getSwitcher;
import static com.trackerforce.queue.config.Features.ASYNC_QUEUE;

@Service
@Slf4j
public class ProcedureProducerService {

	private static final String TOPIC_SUBMISSION = "procedure_submissions";
	
	private static final String TOPIC_NEXT = "procedure_next";
	
	private final KafkaTemplate<String, ProcedureRequest> kafkaProcedureTemplate;

	private final ProcedureService procedureService;
	
	public ProcedureProducerService(KafkaTemplate<String, ProcedureRequest> kafkaProcedureTemplate,
			ProcedureService procedureService) {
		this.kafkaProcedureTemplate = kafkaProcedureTemplate;
		this.procedureService = procedureService;
	}

	public void submitProcedure(final ProcedureRequest procedureRequest) {
		if (getSwitcher(ASYNC_QUEUE).isItOn()) {
			log.info(String.format("#### -> Producing message (submission) -> %s", procedureRequest));
			this.kafkaProcedureTemplate.send(TOPIC_SUBMISSION, procedureRequest);
		} else {
			log.info(String.format("#### -> Sync callback -> %s", procedureRequest));
			procedureService.submitProcedure(procedureRequest);
		}
	}
	
	public void nextProcedure(final ProcedureRequest procedureRequest) {
		if (getSwitcher(ASYNC_QUEUE).isItOn()) {
			log.info(String.format("#### -> Producing message (next) -> %s", procedureRequest));
			this.kafkaProcedureTemplate.send(TOPIC_NEXT, procedureRequest);
		} else {
			log.info(String.format("#### -> Sync callback -> %s", procedureRequest));
			procedureService.nextProcedure(procedureRequest);
		}
	}
}
