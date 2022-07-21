package com.bosonit.docker.student.application.port;

import com.bosonit.docker.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.docker.student.infrastructure.dto.output.StudentOutputDTO;

import java.util.List;

public interface AddStudentPort {
    StudentOutputDTO addStudent(StudentInputDTO studentInputDTO);
    StudentOutputDTO addCourses(String idStudent, List<String> coursesInsert);
}
