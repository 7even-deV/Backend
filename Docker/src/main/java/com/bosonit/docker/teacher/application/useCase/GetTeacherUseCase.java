package com.bosonit.docker.teacher.application.useCase;

import com.bosonit.docker.exception.NotFoundException;
import com.bosonit.docker.teacher.application.port.GetTeacherPort;
import com.bosonit.docker.teacher.domain.Teacher;
import com.bosonit.docker.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.docker.teacher.infrastructure.repository.TeacherRepository;

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
