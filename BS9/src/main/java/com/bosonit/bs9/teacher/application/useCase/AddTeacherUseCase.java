package com.bosonit.bs9.teacher.application.useCase;

import com.bosonit.bs9.exception.NotFoundException;
import com.bosonit.bs9.person.domain.Person;
import com.bosonit.bs9.person.infrastructure.repository.PersonRepository;
import com.bosonit.bs9.teacher.application.port.AddTeacherPort;
import com.bosonit.bs9.teacher.domain.Teacher;
import com.bosonit.bs9.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.bs9.teacher.infrastructure.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTeacherUseCase implements AddTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO) {
        Person person = personRepository.findById(teacherInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + teacherInputDTO.getIdPerson()));
        Teacher teacher = new Teacher(teacherInputDTO);
        teacher.setPerson(person);
        teacherRepository.save(teacher);

        return new TeacherOutputDTO(teacher);
    }
}
