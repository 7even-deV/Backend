package com.bosonit.ej3.teacher.application.port;

import com.bosonit.ej3.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.ej3.teacher.infrastructure.dto.output.TeacherOutputDTO;

public interface UpdateTeacherPort {
    TeacherOutputDTO updateTeacher(String id, TeacherInputDTO teacherInputDTO);
}
