package com.bosonit.bs9.student.application.port;

import com.bosonit.bs9.student.domain.Student;

public interface GetStudentPort {
    Student getStudentId(String id);
}
