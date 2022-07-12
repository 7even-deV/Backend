package com.bosonit.bs9.person.application.port;

import com.bosonit.bs9.person.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.bs9.person.infrastructure.dto.output.PersonOutputDTO;

public interface AddPersonPort {
    PersonOutputDTO addPerson(PersonInputDTO personInputDto);
}
