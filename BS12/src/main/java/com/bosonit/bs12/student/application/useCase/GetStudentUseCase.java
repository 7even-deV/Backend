package com.bosonit.bs12.student.application.useCase;

import com.bosonit.bs12.student.application.port.GetStudentPort;
import com.bosonit.bs12.student.domain.Student;
import com.bosonit.bs12.student.infrastructure.repository.StudentRepository;
import com.bosonit.bs12.exception.NotFoundException;

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
