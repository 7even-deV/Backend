package com.bosonit.backbusiness.utils.kafka.listener;

import com.bosonit.backbusiness.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backbusiness.client.infrastructure.kafka.KafkaClientService;
import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;
import com.bosonit.backbusiness.mail.infrastructure.kafka.KafkaMailService;
import com.bosonit.backbusiness.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backbusiness.ticket.infrastructure.kafka.KafkaTicketService;
import com.bosonit.backbusiness.trip.infrastructure.controller.dto.output.TripOutputDTO;
import com.bosonit.backbusiness.trip.infrastructure.kafka.KafkaTripService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaRouter {

    @Autowired
    KafkaClientService kafkaClientService;

    @Autowired
    KafkaTripService kafkaTripService;

    @Autowired
    KafkaTicketService kafkaTicketService;

    @Autowired
    KafkaMailService kafkaMailService;

    @Value("${server.port}")
    String port;

    @KafkaListener(topics = "${topic}", groupId = "${group}")
    public void listenTopic(@Payload ConsumerRecord<String, Object> record) throws JsonProcessingException {

        final String[] port = new String[1];
        final String[] action = new String[1];
        final String[] type = new String[1];
        ObjectMapper mapper = new ObjectMapper();

        record.headers().headers("port").forEach(header -> {
            port[0] = new String(header.value());
        });

        record.headers().headers("action").forEach(header -> {
            action[0] = new String(header.value());
        });

        record.headers().headers("type").forEach(header -> {
            type[0] = new String(header.value());
        });

        if (!port[0].equals(this.port)) {
            log.info("Message Received!");
            switch (type[0]) {
                case "client" -> {
                    log.info("CLIENT RECEIVED! action: " + action[0]);
                    kafkaClientService.listenTopic(action[0],
                            mapper.readValue((String) record.value(), ClientOutputDTO.class));
                }
                case "ticket" -> {
                    log.info("TICKET RECEIVED! action:" + action[0]);
                    kafkaTicketService.listenTopic(action[0],
                            mapper.readValue((String) record.value(), TicketOutputDTO.class));
                }
                case "trip" -> {
                    log.info("TRIP RECEIVED! action: " + action[0]);
                    kafkaTripService.listenTopic(action[0],
                            mapper.readValue((String) record.value(), TripOutputDTO.class));
                }
                case "mail" -> {
                    log.info("MAIL RECEIVED! action: " + action[0]);
                    kafkaMailService.listenTopic(action[0], mapper.readValue((String) record.value(), Mail.class));
                }
                default -> log.info("I am sorry! something went wrong...");
            }
        }
    }
}
