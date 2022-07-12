package com.bosonit.bs9.person.infrastructure.dto.output;

import com.bosonit.bs9.person.domain.Person;
import com.bosonit.bs9.student.infrastructure.dto.output.StudentOutputDTO;

public class PersonStudentDTO extends PersonOutputDTO {

    public StudentOutputDTO student;

    public PersonStudentDTO(Person person) {
        super(person);
    }
}
