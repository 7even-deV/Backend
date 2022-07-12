package com.bosonit.bs9.student.application.useCase;

import com.bosonit.bs9.student.application.port.GetStudentPort;
import com.bosonit.bs9.student.domain.Student;
import com.bosonit.bs9.student.infrastructure.repository.StudentRepository;
import com.bosonit.bs9.exception.NotFoundException;

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
