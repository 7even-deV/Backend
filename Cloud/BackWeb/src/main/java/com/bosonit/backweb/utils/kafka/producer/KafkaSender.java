package com.bosonit.backweb.utils.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object obj, String port, String action, String type) {
        ObjectMapper mapper = new ObjectMapper();

        String jsonObject = null;
        try {
            jsonObject = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ignored) {
        }

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, jsonObject);
        producerRecord.headers().add("port", port.getBytes());
        producerRecord.headers().add("action", action.getBytes());
        producerRecord.headers().add("type", type.getBytes());

        System.out.println("Message sent!");
        System.out.println(producerRecord);
        kafkaTemplate.send(producerRecord);
    }
}
