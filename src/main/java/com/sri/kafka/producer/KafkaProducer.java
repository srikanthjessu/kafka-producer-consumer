package com.sri.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.sri.model.Customer;

public class KafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

  @Value("${kakfa.topic.customer:customer.t}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, Customer> kafkaTemplate;

  public void produce(Customer customer) {
    LOGGER.info("sending customer='{}'", customer.toString());

    kafkaTemplate.send(topicName, customer);
  }
}