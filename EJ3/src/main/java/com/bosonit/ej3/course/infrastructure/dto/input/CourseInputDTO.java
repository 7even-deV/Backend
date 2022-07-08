package com.bosonit.ej3.course.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CourseInputDTO {

    @NotNull(message = "The course must have a teacher.")
    private String idTeacher;

    @NotNull(message = "The course must have a name.")
    private String name;

    private String comments;

    private Date initialDate;

    private Date finishDate;

    private List<String> idsStudents;
}
