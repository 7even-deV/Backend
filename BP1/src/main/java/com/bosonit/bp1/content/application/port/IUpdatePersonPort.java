package com.bosonit.bp1.content.application.port;

import com.bosonit.bp1.content.infrastructure.controller.dto.input.PersonInputDTO;
import com.bosonit.bp1.content.infrastructure.controller.dto.output.PersonOutputDTO;

public interface IUpdatePersonPort {
    PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDTO) throws Exception;
}
