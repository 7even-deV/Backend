package com.bosonit.backbusiness.mail.infrastructure.controller;

import com.bosonit.backbusiness.mail.application.service.MailService;
import com.bosonit.backbusiness.ticket.application.impl.domain.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/business/mail")
@RestController
public class ReadMail {

    @Autowired
    MailService mailService;

    @GetMapping
    public List<Mail> findAll() {
        return mailService.getAllMail();
    }

    @GetMapping("/details")
    public List<Mail> findByDepartureAndArrival(@RequestParam String departure, @RequestParam String arrival) {
        return mailService.findByDepartureAndArrival(departure, arrival);
    }

    @GetMapping("/detailsLocalDate")
    public List<Mail> findByLocalDate(@RequestParam String localDate) {
        return mailService.findByLocalDate(localDate);
    }

    @GetMapping("/{id}")
    public Mail filterMailById(@PathVariable UUID id) {
        return mailService.filterMailById(id);
    }
}
