package com.bosonit.bs12.student.application.port;

import com.bosonit.bs12.student.domain.Student;

public interface GetStudentPort {
    Student getStudentId(String id);
}
