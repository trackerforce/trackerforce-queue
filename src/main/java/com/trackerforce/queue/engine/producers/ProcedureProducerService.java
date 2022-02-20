package com.trackerforce.queue.engine.producers;

import static com.github.switcherapi.client.SwitcherContext.getSwitcher;
import static com.trackerforce.queue.config.Features.ASYNC_QUEUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.trackerforce.queue.model.ProcedureRequest;
import com.trackerforce.queue.service.ProcedureService;

@Service
public class ProcedureProducerService {

	private static final Logger logger = LoggerFactory.getLogger(ProcedureProducerService.class);

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
			logger.info(String.format("#### -> Producing message (submission) -> %s", procedureRequest));
			this.kafkaProcedureTemplate.send(TOPIC_SUBMISSION, procedureRequest);
		} else {
			logger.info(String.format("#### -> Sync callback -> %s", procedureRequest));
			procedureService.submitProcedure(procedureRequest);
		}
	}
	
	public void nextProcedure(final ProcedureRequest procedureRequest) {
		if (getSwitcher(ASYNC_QUEUE).isItOn()) {
			logger.info(String.format("#### -> Producing message (next) -> %s", procedureRequest));
			this.kafkaProcedureTemplate.send(TOPIC_NEXT, procedureRequest);
		} else {
			logger.info(String.format("#### -> Sync callback -> %s", procedureRequest));
			procedureService.nextProcedure(procedureRequest);
		}
	}
}
