package com.bosonit.docker.teacher.application.port;

import com.bosonit.docker.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.docker.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface AddTeacherPort {
    TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO);
}
