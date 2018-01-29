package com.sri.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.sri.kafka.consumer.KafkaConsumer;
import com.sri.kafka.model.Customer;
import com.sri.kafka.producer.KafkaProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerConsumerApplicationTests {

  @Autowired
  private KafkaProducer kafkaProducer;

  @Autowired
  private KafkaConsumer kafkaConsumer;

  @Autowired
  private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  @ClassRule
  public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "customer.t");

  @Before
  public void setUp() throws Exception {
    // wait until the partitions are assigned
    for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
        .getListenerContainers()) {
      ContainerTestUtils.waitForAssignment(messageListenerContainer,
          embeddedKafka.getPartitionsPerTopic());
    }
  }

  @Test
  public void testReceive() throws Exception {
    Customer customer = new Customer("C1234", "Srikanth", "Jessu", "abcd.cefg@gmail.com");
    kafkaProducer.produce(customer);

    kafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    assertThat(kafkaConsumer.getLatch().getCount()).isEqualTo(0);
  }
}
