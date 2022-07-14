package com.bosonit.bs12.teacher.application.port;

import com.bosonit.bs12.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface AddTeacherPort {
    TeacherOutputDTO addTeacher(TeacherInputDTO teacherInputDTO);
}
