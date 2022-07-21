package com.bosonit.docker.teacher.application.useCase;

import com.bosonit.docker.exception.NotFoundException;
import com.bosonit.docker.person.domain.Person;
import com.bosonit.docker.person.infrastructure.repository.PersonRepository;
import com.bosonit.docker.teacher.application.port.AddTeacherPort;
import com.bosonit.docker.teacher.domain.Teacher;
import com.bosonit.docker.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.docker.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.docker.teacher.infrastructure.repository.TeacherRepository;

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
