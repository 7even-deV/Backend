package com.bosonit.docker.person.application.port;

import com.bosonit.docker.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.docker.person.infrastructure.dto.output.PersonOutputDTO;

public interface UpdatePersonPort {
    PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDto);
}
