package com.bosonit.bs12.teacher.application.port;

import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface GetTeacherPort {
    TeacherOutputDTO getTeacherId(String id);
}
