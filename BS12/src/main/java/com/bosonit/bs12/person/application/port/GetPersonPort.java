package com.bosonit.bs12.person.application.port;

import com.bosonit.bs12.person.infrastructure.dto.output.PersonOutputDTO;

import java.util.List;

public interface GetPersonPort {
    PersonOutputDTO getPersonId(String id);
    List<PersonOutputDTO> getPersonName(String username);
    List<PersonOutputDTO> getPersons();
}
