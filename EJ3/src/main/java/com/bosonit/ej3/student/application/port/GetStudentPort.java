package com.bosonit.ej3.student.application.port;

import com.bosonit.ej3.student.domain.Student;

public interface GetStudentPort {
    Student getStudentId(String id);
}
