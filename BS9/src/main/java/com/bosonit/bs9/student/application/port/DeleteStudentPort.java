package com.bosonit.bs9.student.application.port;

import com.bosonit.bs9.student.infrastructure.dto.output.StudentOutputDTO;

import java.util.List;

public interface DeleteStudentPort {
    void deleteStudent(String id);
    StudentOutputDTO deleteCourses(String idStudent, List<String> coursesDelete);
}
