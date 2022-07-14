package com.bosonit.bs12.student.application.port;

import com.bosonit.bs12.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.bs12.student.infrastructure.dto.output.StudentOutputDTO;

import java.util.List;

public interface AddStudentPort {
    StudentOutputDTO addStudent(StudentInputDTO studentInputDTO);
    StudentOutputDTO addCourses(String idStudent, List<String> coursesInsert);
}
