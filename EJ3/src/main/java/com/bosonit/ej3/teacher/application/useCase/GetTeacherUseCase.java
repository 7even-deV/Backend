package com.bosonit.ej3.teacher.application.useCase;

import com.bosonit.ej3.exception.NotFoundException;
import com.bosonit.ej3.teacher.application.port.GetTeacherPort;
import com.bosonit.ej3.teacher.domain.Teacher;
import com.bosonit.ej3.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.ej3.teacher.infrastructure.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTeacherUseCase implements GetTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public TeacherOutputDTO getTeacherId(String id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No teacher found with id: " + id));

        return new TeacherOutputDTO(teacher);
    }
}
