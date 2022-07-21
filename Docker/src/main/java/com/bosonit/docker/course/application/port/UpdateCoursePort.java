package com.bosonit.docker.course.application.port;

import com.bosonit.docker.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.docker.course.infrastructure.dto.output.CourseOutputDTO;

public interface UpdateCoursePort {
    CourseOutputDTO updateCourse(String id, CourseInputDTO courseInputDTO);
}
