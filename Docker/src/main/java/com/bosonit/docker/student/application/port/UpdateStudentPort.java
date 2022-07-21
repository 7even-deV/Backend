package com.bosonit.docker.student.application.port;

import com.bosonit.docker.student.domain.Student;
import com.bosonit.docker.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.docker.student.infrastructure.dto.output.StudentOutputDTO;

public interface UpdateStudentPort {
    Student getStudentId(String id);
    StudentOutputDTO updateStudent(String id, StudentInputDTO studentInputDTO);
}
