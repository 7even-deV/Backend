package com.bosonit.bs12.teacher.application.useCase;

import com.bosonit.bs12.exception.NotFoundException;
import com.bosonit.bs12.person.domain.Person;
import com.bosonit.bs12.person.infrastructure.repository.PersonRepository;
import com.bosonit.bs12.teacher.application.port.AddTeacherPort;
import com.bosonit.bs12.teacher.domain.Teacher;
import com.bosonit.bs12.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.bs12.teacher.infrastructure.repository.TeacherRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTeacherUseCase implements AddTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public TeacherOutputDTO addTeacher(@NotNull TeacherInputDTO teacherInputDTO) {
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + teacherInputDTO.getIdPerson()));
        Teacher teacher = new Teacher(teacherInputDTO);
        teacher.setPerson(person);
        teacherRepository.save(teacher);

        return new TeacherOutputDTO(teacher);
    }
}
