package com.bosonit.docker.student.infrastructure.dto.output;

import com.bosonit.docker.course.domain.Course;
import com.bosonit.docker.student.domain.Student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentOutputDTO {

    private String idStudent;
    private String idPerson;
    private String idTeacher;
    private int numHoursWeek;
    private String comments;
    private String branch;
    private List<String> courses = new ArrayList<>();

    public StudentOutputDTO(@NotNull Student student) {
        setIdStudent(student.getIdStudent());
        setIdPerson(student.getPerson().getIdPerson());
        setIdTeacher(student.getTeacher().getIdTeacher());
        setNumHoursWeek(student.getNumHoursWeek());
        setComments(student.getComments());
        setBranch(student.getBranch());
        setCourses(student.getCourses().stream().map(Course::getIdCourse).toList());
    }
}
