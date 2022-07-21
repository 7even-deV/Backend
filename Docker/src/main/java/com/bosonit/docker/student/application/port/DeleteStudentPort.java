package com.bosonit.docker.student.application.port;

import com.bosonit.docker.student.infrastructure.dto.output.StudentOutputDTO;

import java.util.List;

public interface DeleteStudentPort {
    void deleteStudent(String id);
    StudentOutputDTO deleteCourses(String idStudent, List<String> coursesDelete);
}
