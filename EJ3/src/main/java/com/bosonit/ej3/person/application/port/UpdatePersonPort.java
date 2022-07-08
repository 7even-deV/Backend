package com.bosonit.ej3.person.application.port;

import com.bosonit.ej3.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.ej3.person.infrastructure.dto.output.PersonOutputDTO;

public interface UpdatePersonPort {
    PersonOutputDTO updatePerson(String id, PersonInputDTO personInputDto);
}
