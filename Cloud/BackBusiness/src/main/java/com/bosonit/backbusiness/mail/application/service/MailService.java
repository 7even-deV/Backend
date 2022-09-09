package com.bosonit.backbusiness.mail.application.service;

import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;

import java.util.List;
import java.util.UUID;

public interface MailService {

    List<Mail> getAllMail();
    Mail filterMailById(UUID id);
    List<Mail> findByDepartureAndArrival(String departure, String arrival);
    List<Mail> findByLocalDate(String date);
}
