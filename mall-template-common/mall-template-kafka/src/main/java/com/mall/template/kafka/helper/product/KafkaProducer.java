package com.mall.template.kafka.helper.product;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer<K, V> {

	private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<K, V> kafkaTemplate;

	private static final int RETRY_SEND = 3;

	/**
	 * 默认的分区策略
	 * 
	 * @param topic
	 * @param message
	 */
	public void send(String topic, V message) {
		send(topic, null, message);
	}

	/**
	 * 根据key转分区策略
	 * 
	 * @param topic
	 * @param key
	 * @param message
	 */
	public boolean send(String topic, K key, V message) {
		int retryCount = 1;
		if (childSend(topic, key, message)) {
			return true;
		}
		while (++retryCount <= RETRY_SEND) {
			logger.info("进入重试，重试次数： {}",retryCount);
			if (childSend(topic, key, message)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean childSend(String topic, K key, V message) {
		
		ProducerRecord<K, V> producerRecord = key != null ? new ProducerRecord<>(topic, key, message)
				: new ProducerRecord<>(topic, message);
		logger.info("producerRecord:{}",producerRecord.toString());
		Future<SendResult<K, V>> future = kafkaTemplate.send(producerRecord);
		SendResult<K, V> sendResult = null;
		try {
			sendResult = future.get(2000, TimeUnit.SECONDS);
			return true;
		} catch (InterruptedException e) {
			logger.warn("InterruptedException:{}", e.getMessage());
		} catch (ExecutionException e) {
			logger.warn("ExecutionException:{}", e.getMessage());
		} catch (TimeoutException e) {
			logger.warn("TimeoutException:{}", e.getMessage());
		} catch (Exception e) {
			logger.warn("Exception:{}", e.getMessage());
		} finally {
			if(sendResult!=null){
				RecordMetadata recordMetadata = sendResult.getRecordMetadata();
				if(recordMetadata!=null){
					logger.info("recordMetadata:{}",recordMetadata.toString());
				}
			}
		}
		return false;
	}

}
