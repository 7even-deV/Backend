package com.bosonit.ej3.course.application.useCase;

import com.bosonit.ej3.course.application.port.DeleteCoursePort;
import com.bosonit.ej3.course.infrastructure.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourseUseCase implements DeleteCoursePort {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
