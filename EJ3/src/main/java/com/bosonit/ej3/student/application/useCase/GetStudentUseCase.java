package com.bosonit.ej3.student.application.useCase;

import com.bosonit.ej3.student.application.port.GetStudentPort;
import com.bosonit.ej3.student.domain.Student;
import com.bosonit.ej3.student.infrastructure.repository.StudentRepository;
import com.bosonit.ej3.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetStudentUseCase implements GetStudentPort {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getStudentId(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No student found with id: " + id));
    }
}
