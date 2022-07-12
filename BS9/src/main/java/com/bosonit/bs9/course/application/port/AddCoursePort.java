package com.bosonit.bs9.course.application.port;

import com.bosonit.bs9.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.bs9.course.infrastructure.dto.output.CourseOutputDTO;

public interface AddCoursePort {
    CourseOutputDTO addCourse(CourseInputDTO courseInputDTO);
}
