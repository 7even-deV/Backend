package com.bosonit.bs8.content.application.port;

import com.bosonit.bs8.content.infrastructure.controller.dto.output.PersonOutputDTO;

import java.util.List;

public interface IGetPersonPort {
    PersonOutputDTO getPersonId(int id);
    List<PersonOutputDTO> getPersonName(String name);
    List<PersonOutputDTO> getPersons();
}
