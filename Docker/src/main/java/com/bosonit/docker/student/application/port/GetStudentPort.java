package com.bosonit.docker.student.application.port;

import com.bosonit.docker.student.domain.Student;

public interface GetStudentPort {
    Student getStudentId(String id);
}
