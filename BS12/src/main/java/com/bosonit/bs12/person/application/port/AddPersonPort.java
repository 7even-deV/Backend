package com.bosonit.bs12.person.application.port;

import com.bosonit.bs12.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.bs12.person.infrastructure.dto.output.PersonOutputDTO;

public interface AddPersonPort {
    PersonOutputDTO addPerson(PersonInputDTO personInputDto);
}
