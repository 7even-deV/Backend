package com.bosonit.docker.student.domain;

import com.bosonit.docker.PersonSequenceIdGenerator;
import com.bosonit.docker.course.domain.Course;
import com.bosonit.docker.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.docker.person.domain.Person;
import com.bosonit.docker.teacher.domain.Teacher;

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
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPerson")
    @GenericGenerator(name = "idPerson", strategy = "com.bosonit.docker.PersonSequenceIdGenerator", parameters = {
            @Parameter(name = PersonSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = PersonSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "s"),
            @Parameter(name = PersonSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String idStudent;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @Column
    private int numHoursWeek;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "idTeacher")
    Teacher teacher;

    @Column
    String branch;

    @OneToMany
    private List<Course> courses;

    public Student(@NotNull StudentInputDTO studentInputDTO) {
        setNumHoursWeek(studentInputDTO.getNumHoursWeek());
        setComments(studentInputDTO.getComments());
        setBranch(studentInputDTO.getBranch());
    }

    public void update(@NotNull StudentInputDTO studentInputDTO) {
        setNumHoursWeek(studentInputDTO.getNumHoursWeek());
        setComments(studentInputDTO.getComments());
        setBranch(studentInputDTO.getBranch());
    }
}
