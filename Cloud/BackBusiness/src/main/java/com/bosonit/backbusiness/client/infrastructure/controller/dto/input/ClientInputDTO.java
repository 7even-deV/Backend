package com.bosonit.backbusiness.client.infrastructure.controller.dto.input;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInputDTO implements Serializable {

    @NonNull
    private String name;

    private String surname;

    @NonNull
    private String email;

    @NonNull
    private String password;
}
