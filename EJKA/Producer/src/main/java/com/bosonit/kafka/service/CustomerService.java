package com.bosonit.kafka.service;

import com.bosonit.kafka.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerEventsService customerEventsService;

    public Customer save(Customer customer) {
        System.out.println("Received " + customer);
        this.customerEventsService.publish(customer);
        return customer;
    }
}
