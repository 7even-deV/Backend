package com.bosonit.dba1.application.impls;

import com.bosonit.dba1.application.interfaces.IPersonService;
import com.bosonit.dba1.domain.Person;
import com.bosonit.dba1.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.dba1.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.dba1.infrastructure.repository.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public PersonOutputDTO addPerson(PersonInputDTO personInputDTO) throws Exception {
        Person person = new Person(personInputDTO);
        iPersonRepository.save(person);
        return new PersonOutputDTO(person);
    }

    @Override
    public PersonOutputDTO getPersonId(int id) throws Exception {
        Person person = iPersonRepository.findById(id).orElseThrow(() -> new Exception("No person found with ID"));
        return new PersonOutputDTO(person);
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String username) throws Exception {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        iPersonRepository.findByName(username).forEach(person -> {
            tempList.add(new PersonOutputDTO(person));
        });
        return tempList;
    }

    @Override
    public List<PersonOutputDTO> getPersons() {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        iPersonRepository.findAll().forEach(person -> {
            PersonOutputDTO personOutputDTO = new PersonOutputDTO(person);
            tempList.add(personOutputDTO);
        });
        return tempList;
    }

    @Override
    public Page<Person> getPersonsPageable(Pageable pageable) {
        return iPersonRepository.findAll(pageable);
    }
}
