package com.bosonit.bs12.teacher.application.useCase;

import com.bosonit.bs12.exception.NotFoundException;
import com.bosonit.bs12.person.domain.Person;
import com.bosonit.bs12.person.infrastructure.repository.PersonRepository;
import com.bosonit.bs12.teacher.application.port.UpdateTeacherPort;
import com.bosonit.bs12.teacher.domain.Teacher;
import com.bosonit.bs12.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.bs12.teacher.infrastructure.repository.TeacherRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTeacherUseCase implements UpdateTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public TeacherOutputDTO updateTeacher(String id, @NotNull TeacherInputDTO teacherInputDTO) {
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
