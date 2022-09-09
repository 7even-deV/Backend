package com.bosonit.backweb.mail.infrastructure.kafka;

import com.bosonit.backweb.mail.domain.Mail;
import com.bosonit.backweb.mail.infrastructure.repository.MailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaMailService {

    @Autowired
    MailRepository mailRepository;

    public void listenTopic(String action, Mail mail) {
        switch (action) {
            case "create" -> {
                System.out.println(mail);
                mailRepository.save(mail);
                System.out.println("CREATE SUCCESS");
            }
        }
    }
}
