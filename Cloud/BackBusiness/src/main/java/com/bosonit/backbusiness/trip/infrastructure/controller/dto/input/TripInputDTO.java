package com.bosonit.backbusiness.trip.infrastructure.controller.dto.input;

import lombok.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TripInputDTO implements Serializable {

    @NonNull
    private Date date;

    @NonNull
    private String departure;

    @NonNull
    private String arrival;

    @NonNull
    private boolean issue;
}
