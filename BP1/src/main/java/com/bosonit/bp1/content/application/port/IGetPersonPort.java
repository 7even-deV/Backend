package com.bosonit.bp1.content.application.port;

import com.bosonit.bp1.content.infrastructure.controller.dto.output.PersonOutputDTO;

import java.util.List;

public interface IGetPersonPort {
    PersonOutputDTO getPersonId(int id) throws Exception;
    List<PersonOutputDTO> getPersonName(String name) throws Exception;
    List<PersonOutputDTO> getPersons();
}
