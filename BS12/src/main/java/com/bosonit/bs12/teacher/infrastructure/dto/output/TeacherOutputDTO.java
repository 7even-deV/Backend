package com.bosonit.bs12.teacher.infrastructure.dto.output;

import com.bosonit.bs12.teacher.domain.Teacher;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class TeacherOutputDTO {

    private String idTeacher;
    private String idPerson;
    private String comments;
    private String branch;

    public TeacherOutputDTO(@NotNull Teacher teacher) {
        setIdTeacher(teacher.getIdTeacher());
        setIdPerson(teacher.getPerson().getIdPerson());
        setComments(teacher.getComments());
        setBranch(teacher.getBranch());
    }
}
