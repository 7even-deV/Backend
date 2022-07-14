package com.bosonit.bs12.student.application.useCase;

import com.bosonit.bs12.student.application.port.UpdateStudentPort;
import com.bosonit.bs12.student.domain.Student;
import com.bosonit.bs12.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.bs12.student.infrastructure.dto.output.StudentOutputDTO;
import com.bosonit.bs12.student.infrastructure.repository.StudentRepository;
import com.bosonit.bs12.exception.NotFoundException;
import com.bosonit.bs12.person.domain.Person;
import com.bosonit.bs12.person.infrastructure.repository.PersonRepository;

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
