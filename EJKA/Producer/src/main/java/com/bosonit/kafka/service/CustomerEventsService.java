package com.bosonit.kafka.service;

import com.bosonit.kafka.entity.Customer;
import com.bosonit.kafka.events.CustomerCreatedEvent;
import com.bosonit.kafka.events.Event;
import com.bosonit.kafka.events.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CustomerEventsService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.customer.name:customers}")
    private String topicCustomer;

    public void publish(Customer customer) {

        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setId(UUID.randomUUID().toString());
        created.setDate(new Date());
        created.setType(EventType.CREATED);
        created.setData(customer);

        this.producer.send(topicCustomer, created);
    }
}
