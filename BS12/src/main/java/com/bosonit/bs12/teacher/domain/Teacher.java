package com.bosonit.bs12.teacher.domain;

import com.bosonit.bs12.PersonSequenceIdGenerator;
import com.bosonit.bs12.course.domain.Course;
import com.bosonit.bs12.student.domain.Student;
import com.bosonit.bs12.person.domain.Person;
import com.bosonit.bs12.teacher.infrastructure.dto.input.TeacherInputDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPerson")
    @GenericGenerator(name = "idPerson", strategy = "com.bosonit.bs12.PersonSequenceIdGenerator", parameters = {
            @Parameter(name = PersonSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = PersonSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "t"),
            @Parameter(name = PersonSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String idTeacher;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column
    private String comments;

    @Column
    private String branch;

    @OneToMany
    private List<Course> courses;

    @OneToMany
    private List<Student> students;

    public Teacher(@NotNull TeacherInputDTO teacherInputDTO) {
        setComments(teacherInputDTO.getComments());
        setBranch(teacherInputDTO.getBranch());
    }

    public void update(@NotNull TeacherInputDTO teacherInputDTO) {
        if (teacherInputDTO.getComments() != null) {
            setComments(teacherInputDTO.getComments());
        }
        if (teacherInputDTO.getBranch() != null) {
            setBranch(teacherInputDTO.getBranch());
        }
    }
}
