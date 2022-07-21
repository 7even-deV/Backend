package com.bosonit.docker.person.application.port;

import com.bosonit.docker.person.infrastructure.dto.output.PersonOutputDTO;

import java.util.List;

public interface GetPersonPort {
    PersonOutputDTO getPersonId(String id);
    List<PersonOutputDTO> getPersonName(String username);
    List<PersonOutputDTO> getPersons();
}
