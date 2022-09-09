package com.bosonit.backbusiness.ticket.application.impl.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @Id
    @Column(name = "id_mail")
    private UUID idMail;

    private Date date;

    private String departure;

    private String arrival;

    private String to;

    private String subject;
}
