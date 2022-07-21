package com.bosonit.docker.student.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentInputDTO {

    @NotNull(message = "Student data cannot be empty.")
    private String idPerson;

    private String idTeacher;

    private int numHoursWeek;

    private String comments;

    @NotEmpty(message = "The Student must have a Teacher.")
    private String teacher;

    @NotEmpty(message = "The Student must belong to a branch.")
    private String branch;

    private List<String> courses = new ArrayList<>();
}
