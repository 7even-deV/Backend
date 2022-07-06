package com.bosonit.ej2.application.interfaces;

import com.bosonit.ej2.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.ej2.infrastructure.dto.output.PersonOutputDTO;

import java.util.List;

public interface IPersonService {

    PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception;
    PersonOutputDTO getPersonId(int id) throws Exception;
    List<PersonOutputDTO> getPersonName(String username) throws Exception;
    List<PersonOutputDTO> getPersons();
}
