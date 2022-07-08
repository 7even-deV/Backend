package com.bosonit.ej3.teacher.application.port;

import com.bosonit.ej3.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface GetTeacherPort {
    TeacherOutputDTO getTeacherId(String id);
}
