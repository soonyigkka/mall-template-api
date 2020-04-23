package com.mall.template.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ClassLearnListener {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@KafkaListener(topics = { "cluster-sample" },group="student-1")
	public void listenClassLearn(String data,Acknowledgment ack) {
		logger.info("============student learn class : " + data);
		ack.acknowledge();
	}
}
