package com.sri.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.sri.model.Customer;

public class KafkaConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${kakfa.topic.customer}")
	public void receive(Customer customer) {
		LOGGER.info("received customer='{}'", customer.toString());
		latch.countDown();
	}
}
