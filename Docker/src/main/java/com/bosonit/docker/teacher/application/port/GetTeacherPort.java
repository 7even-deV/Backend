package com.bosonit.docker.teacher.application.port;

import com.bosonit.docker.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface GetTeacherPort {
    TeacherOutputDTO getTeacherId(String id);
}
