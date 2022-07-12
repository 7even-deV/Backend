package com.bosonit.bs9.teacher.application.port;

import com.bosonit.bs9.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface UpdateTeacherPort {
    TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacherInputDTO);
}
