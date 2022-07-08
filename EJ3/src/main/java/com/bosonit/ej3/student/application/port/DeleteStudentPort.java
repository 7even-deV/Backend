package com.bosonit.ej3.student.application.port;

import com.bosonit.ej3.student.infrastructure.dto.output.StudentOutputDTO;

import java.util.List;

public interface DeleteStudentPort {
    void deleteStudent(String id);
    StudentOutputDTO deleteCourses(String idStudent, List<String> coursesDelete);
}
