package com.sri.kafka;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sri.kafka.consumer.CustomerRepository;
import com.sri.kafka.consumer.CustomerService;
import com.sri.kafka.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void testAdd() {
		
		Customer customer = new Customer("C1234", "Srikanth", "Jessu", "abcd.cefg@gmail.com");
		customerService.add(customer);
		
		Customer theCustomer = customerRepository.findOne("C1234");
		
		assertEquals(customer.getId(), theCustomer.getId());
	
		
	}
}
