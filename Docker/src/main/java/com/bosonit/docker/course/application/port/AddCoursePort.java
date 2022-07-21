package com.bosonit.docker.course.application.port;

import com.bosonit.docker.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.docker.course.infrastructure.dto.output.CourseOutputDTO;

public interface AddCoursePort {
    CourseOutputDTO addCourse(CourseInputDTO courseInputDTO);
}
