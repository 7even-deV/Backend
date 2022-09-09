package com.bosonit.backweb.utils.kafka.listener;

import com.bosonit.backweb.client.infrastructure.controller.dto.output.ClientOutputDTO;
import com.bosonit.backweb.client.infrastructure.kafka.KafkaClientService;
import com.bosonit.backweb.mail.domain.Mail;
import com.bosonit.backweb.mail.infrastructure.kafka.KafkaMailService;
import com.bosonit.backweb.ticket.infrastructure.controller.dto.output.TicketOutputDTO;
import com.bosonit.backweb.ticket.infrastructure.kafka.KafkaTicketService;
import com.bosonit.backweb.trip.infrastructure.controller.dto.output.TripOutputDTO;
import com.bosonit.backweb.trip.infrastructure.kafka.KafkaTripService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

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
            System.out.println("Message received!");
            switch (type[0]) {
                case "client" -> {
                    System.out.println("CLIENT RECEIVED! action: " + action[0]);
                    kafkaClientService.listenTopic(action[0],
                            mapper.readValue((String) record.value(), ClientOutputDTO.class));
                }
                case "trip" -> {
                    System.out.println("TRIP RECEIVED! action: " + action[0]);
                    kafkaTripService.listenTopic(action[0],
                            mapper.readValue((String) record.value(), TripOutputDTO.class));
                }
                case "ticket" -> {
                    System.out.println("TICKET RECEIVED! action:" + action[0]);
                    kafkaTicketService.listenTopic(action[0],
                            mapper.readValue((String) record.value(), TicketOutputDTO.class));
                }
                case "mail" -> {
                    System.out.println("MAIL RECEIVED! action: " + action[0]);
                    kafkaMailService.listenTopic(action[0], mapper.readValue((String) record.value(), Mail.class));
                }
                default -> System.out.println("Error!");
            }
        }
    }
}
