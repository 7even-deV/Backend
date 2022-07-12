package com.bosonit.bs9.teacher.application.useCase;

import com.bosonit.bs9.exception.NotFoundException;
import com.bosonit.bs9.person.domain.Person;
import com.bosonit.bs9.person.infrastructure.repository.PersonRepository;
import com.bosonit.bs9.teacher.application.port.UpdateTeacherPort;
import com.bosonit.bs9.teacher.domain.Teacher;
import com.bosonit.bs9.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.bs9.teacher.infrastructure.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTeacherUseCase implements UpdateTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacherInputDTO) {
        Teacher foundTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No teacher found with id: " + id));
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + id));
        foundTeacher.update(teacherInputDTO);
        foundTeacher.setPerson(person);
        teacherRepository.save(foundTeacher);

        return new TeacherOutputDTO(foundTeacher);
    }
}
