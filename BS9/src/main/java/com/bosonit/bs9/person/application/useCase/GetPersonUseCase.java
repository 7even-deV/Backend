package com.bosonit.bs9.person.application.useCase;

import com.bosonit.bs9.exception.NotFoundException;
import com.bosonit.bs9.person.application.port.GetPersonPort;
import com.bosonit.bs9.person.domain.Person;
import com.bosonit.bs9.person.infrastructure.dto.output.PersonOutputDTO;
import com.bosonit.bs9.person.infrastructure.dto.output.PersonStudentDTO;
import com.bosonit.bs9.person.infrastructure.dto.output.PersonTeacherDTO;
import com.bosonit.bs9.person.infrastructure.repository.PersonRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPersonUseCase implements GetPersonPort {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<PersonOutputDTO> getPersonId(String id, String outputType) throws Exception {

        PersonOutputDTO personOutputDTO;
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No person found with id: " + id));

        if (outputType.equals("full")) {
            if (person.getStudent() != null) {
                PersonStudentDTO studentOutputDTO = modelMapper.map(person, PersonStudentDTO.class);
                return ResponseEntity.ok().body(studentOutputDTO);
            } else if (person.getTeacher() != null) {
                PersonTeacherDTO teacherOutputDTO = modelMapper.map(person, PersonTeacherDTO.class);
                return ResponseEntity.ok().body(teacherOutputDTO);
            } else
                throw new NotFoundException("No person found with id: " + id);
        } else
            return ResponseEntity.ok().body(modelMapper.map(person, PersonOutputDTO.class));
    }

    @Override
    public PersonOutputDTO getPersonId(String id) {
        return null;
    }

    @Override
    public List<PersonOutputDTO> getPersonName(String username) {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        personRepository.findByName(username).forEach(person -> {
            tempList.add(new PersonOutputDTO(person));
        });

        return tempList;
    }

    @Override
    public List<PersonOutputDTO> getPersons() {
        List<PersonOutputDTO> tempList = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            PersonOutputDTO personOutputDto = new PersonOutputDTO(person);
            tempList.add(personOutputDto);
        });

        return tempList;
    }
}
