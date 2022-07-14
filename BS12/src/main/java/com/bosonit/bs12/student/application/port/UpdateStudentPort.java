package com.bosonit.bs12.student.application.port;

import com.bosonit.bs12.student.domain.Student;
import com.bosonit.bs12.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.bs12.student.infrastructure.dto.output.StudentOutputDTO;

public interface UpdateStudentPort {
    Student getStudentId(String id);
    StudentOutputDTO updateStudent(String id, StudentInputDTO studentInputDTO);
}
