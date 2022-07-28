package com.bosonit.dba1.application.interfaces;

import com.bosonit.dba1.domain.Person;
import com.bosonit.dba1.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.dba1.infrastructure.dto.output.PersonOutputDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {

    PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception;
    PersonOutputDTO getPersonId(int id) throws Exception;
    List<PersonOutputDTO> getPersonName(String username) throws Exception;
    List<PersonOutputDTO> getPersons();
    Page<Person> getPersonsPageable(Pageable pageable);
}
