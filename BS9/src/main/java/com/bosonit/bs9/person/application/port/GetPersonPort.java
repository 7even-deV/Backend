package com.bosonit.bs9.person.application.port;

import com.bosonit.bs9.person.infrastructure.dto.output.PersonOutputDTO;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GetPersonPort {
    PersonOutputDTO getPersonId(String id);
    List<PersonOutputDTO> getPersonName(String username);
    List<PersonOutputDTO> getPersons();

    ResponseEntity<PersonOutputDTO> getPersonId(String id, String outputType) throws Exception;
}
