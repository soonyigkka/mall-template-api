package com.mall.template.kafka.helper.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer<K,V> {
    
	@Autowired
    private KafkaTemplate<K, V> kafkaTemplate;
    
    /**
     * 默认的分区策略
     * @param topic
     * @param message
     */
    public void send(String topic ,V message) {
        kafkaTemplate.send(topic, message);
    }
    
    /**
     * 根据key转分区策略
     * @param topic
     * @param key
     * @param message
     */
    public void send(String topic ,K key,V message) {
        kafkaTemplate.send(topic, key , message);
    }
}
