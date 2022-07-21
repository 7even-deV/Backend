package com.bosonit.docker.student.application.useCase;

import com.bosonit.docker.student.application.port.UpdateStudentPort;
import com.bosonit.docker.student.domain.Student;
import com.bosonit.docker.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.docker.student.infrastructure.dto.output.StudentOutputDTO;
import com.bosonit.docker.student.infrastructure.repository.StudentRepository;
import com.bosonit.docker.exception.NotFoundException;
import com.bosonit.docker.person.domain.Person;
import com.bosonit.docker.person.infrastructure.repository.PersonRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStudentUseCase implements UpdateStudentPort {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    public Student getStudentId(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No student found with id: " + id));
    }

    @Override
    public StudentOutputDTO updateStudent(String id, @NotNull StudentInputDTO studentInputDTO) {
        Student foundStudent = getStudentId(id);
        Person person = personRepository.findById(studentInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + id));
        foundStudent.update(studentInputDTO);
        foundStudent.setPerson(person);
        studentRepository.save(foundStudent);
        return new StudentOutputDTO(foundStudent);
    }
}
