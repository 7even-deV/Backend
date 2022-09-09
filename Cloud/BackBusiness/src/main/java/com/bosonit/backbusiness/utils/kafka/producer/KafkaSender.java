package com.bosonit.backbusiness.utils.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
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
            log.info("EXCEPTION IN JSON PROCESSING");
        }

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, jsonObject);
        producerRecord.headers().add("port", port.getBytes());
        producerRecord.headers().add("action", action.getBytes());
        producerRecord.headers().add("type", type.getBytes());

        log.info("Message sent!");
        log.info("The message is: " + producerRecord);
        kafkaTemplate.send(producerRecord);
    }
}
