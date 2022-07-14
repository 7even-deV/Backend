package com.bosonit.bs12.teacher.application.useCase;

import com.bosonit.bs12.exception.NotFoundException;
import com.bosonit.bs12.teacher.application.port.GetTeacherPort;
import com.bosonit.bs12.teacher.domain.Teacher;
import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.bs12.teacher.infrastructure.repository.TeacherRepository;

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
