package com.bosonit.bs9.teacher.application.port;

import com.bosonit.bs9.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface AddTeacherPort {
    TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO);
}
