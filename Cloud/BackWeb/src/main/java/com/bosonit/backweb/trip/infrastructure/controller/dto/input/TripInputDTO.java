package com.bosonit.backweb.trip.infrastructure.controller.dto.input;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TripInputDTO implements Serializable {

    @NotNull
    private Date date;

    @NotNull
    private String departure;

    @NotNull
    private String arrival;

    @NotNull
    private boolean issue;

}
