package com.bosonit.bs9.person.infrastructure.dto.output;

import com.bosonit.bs9.person.domain.Person;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

public class PersonTeacherDTO extends PersonOutputDTO {

    public TeacherOutputDTO teacher;

    public PersonTeacherDTO(Person person) {
        super(person);
    }
}
