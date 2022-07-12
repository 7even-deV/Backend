package com.bosonit.bs9.teacher.application.useCase;

import com.bosonit.bs9.teacher.application.port.DeleteTeacherPort;
import com.bosonit.bs9.teacher.infrastructure.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTeacherUseCase implements DeleteTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public void deleteTeacher(String id) {
        teacherRepository.deleteById(id);
    }
}
