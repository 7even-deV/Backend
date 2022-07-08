package com.bosonit.ej3.course.application.port;

import com.bosonit.ej3.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.ej3.course.infrastructure.dto.output.CourseOutputDTO;

public interface UpdateCoursePort {
    CourseOutputDTO updateCourse(String id, CourseInputDTO courseInputDTO);
}
