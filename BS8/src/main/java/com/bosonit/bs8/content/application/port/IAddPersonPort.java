package com.bosonit.bs8.content.application.port;

import com.bosonit.bs8.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bs8.content.infrastructure.controller.dto.output.PersonOutputDTO;

public interface IAddPersonPort {
    PersonOutputDTO addPerson(PersonInputDTO personInputDTO);
}
