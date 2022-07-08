package com.bosonit.ej3.course.domain;

import com.bosonit.ej3.PersonSequenceIdGenerator;
import com.bosonit.ej3.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.ej3.student.domain.Student;
import com.bosonit.ej3.teacher.domain.Teacher;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPerson")
    @GenericGenerator(name = "idPerson", strategy = "com.bosonit.ej3.PersonSequenceIdGenerator", parameters = {
            @Parameter(name = PersonSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = PersonSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "c"),
            @Parameter(name = PersonSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String idCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "course")
    private List<Student> students = new ArrayList<>();

    @Column
    private String name;

    @Column
    private String comments;

    @Column
    private Date initialDate;

    @Column
    private Date finishDate;

    public Course(@NotNull CourseInputDTO courseInputDto) {
        setName(courseInputDto.getName());
        setComments(courseInputDto.getComments());
        setInitialDate(courseInputDto.getInitialDate());
        setFinishDate(courseInputDto.getFinishDate());
    }

    public void update(@NotNull CourseInputDTO courseInputDTO) {
        if (courseInputDTO.getName() != null) {
            setName(courseInputDTO.getName());
        }
        if (courseInputDTO.getInitialDate() != null) {
            setInitialDate(courseInputDTO.getInitialDate());
        }
        if (courseInputDTO.getFinishDate() != null) {
            setFinishDate(courseInputDTO.getFinishDate());
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }
}
