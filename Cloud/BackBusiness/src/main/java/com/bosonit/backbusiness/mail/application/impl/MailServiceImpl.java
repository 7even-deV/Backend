package com.bosonit.backbusiness.mail.application.impl;

import com.bosonit.backbusiness.mail.application.service.MailService;
import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;
import com.bosonit.backbusiness.mail.infrastructure.repository.MailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    MailRepository mailRepository;

    @Override
    public List<Mail> getAllMail() {
        List<Mail> mails = mailRepository.findAll();
        return mails.stream().toList();
    }

    @Override
    public Mail filterMailById(UUID id) {
        return mailRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Mail> findByDepartureAndArrival(String departure, String arrival) {
        List<Mail> mails = mailRepository.findByDepartureAndArrival(departure, arrival);
        return mails.stream().toList();
    }

    @Override
    public List<Mail> findByLocalDate(String date) {
        List<Mail> mails = mailRepository.findByLocalDate(date);
        return mails.stream().toList();
    }
}
