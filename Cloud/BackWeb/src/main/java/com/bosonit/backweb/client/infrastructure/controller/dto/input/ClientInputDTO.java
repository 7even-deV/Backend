package com.bosonit.backweb.client.infrastructure.controller.dto.input;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientInputDTO implements Serializable {

    @NotNull
    private String name;

    private String surname;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
