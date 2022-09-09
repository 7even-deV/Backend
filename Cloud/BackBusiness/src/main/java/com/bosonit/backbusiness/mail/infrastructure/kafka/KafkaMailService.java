package com.bosonit.backbusiness.mail.infrastructure.kafka;

import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;
import com.bosonit.backbusiness.mail.infrastructure.repository.MailRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMailService {

    @Autowired
    MailRepository mailRepository;

    public void listenTopic(String action, Mail mail) {
        switch (action) {
            case "create" -> {
                mailRepository.save(mail);

                log.info("CREATE SUCCESS");
            }
            default -> {
                log.info("ERROR KAFKA SERVICE MAIL! UNSPECIFIED ACTION (create)");
            }
        }
    }
}
