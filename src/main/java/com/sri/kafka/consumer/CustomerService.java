package com.sri.kafka.consumer;

import org.springframework.stereotype.Service;

import com.sri.kafka.model.Customer;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository theCustomerRepository) {
		this.customerRepository =  theCustomerRepository;
	}
	public void add(Customer customer) {
		customerRepository.save(customer);
	}

}
