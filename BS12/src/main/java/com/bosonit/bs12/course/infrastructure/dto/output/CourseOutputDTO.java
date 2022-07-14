package com.bosonit.bs12.course.infrastructure.dto.output;

import com.bosonit.bs12.course.domain.Course;
import com.bosonit.bs12.student.domain.Student;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CourseOutputDTO {

    private String idCourse;
    private String idTeacher;
    private String name;
    private String comments;
    private Date initialDate;
    private Date finishDate;
    private List<String> idsStudents = new ArrayList<>();

    public CourseOutputDTO(@NotNull Course course) {
        setIdCourse(course.getIdCourse());
        setName(course.getName());
        setComments(course.getComments());
        setInitialDate(course.getInitialDate());
        setFinishDate(course.getFinishDate());
        setIdsStudents(course.getStudents().stream().map(Student::getIdStudent).collect(Collectors.toList()));
    }
}
