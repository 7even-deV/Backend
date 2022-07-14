package com.bosonit.bs12.student.application.port;

import com.bosonit.bs12.student.infrastructure.dto.output.StudentOutputDTO;

import java.util.List;

public interface DeleteStudentPort {
    void deleteStudent(String id);
    StudentOutputDTO deleteCourses(String idStudent, List<String> coursesDelete);
}
