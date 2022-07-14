package com.bosonit.bs12.course.application.port;

import com.bosonit.bs12.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.bs12.course.infrastructure.dto.output.CourseOutputDTO;

public interface AddCoursePort {
    CourseOutputDTO addCourse(CourseInputDTO courseInputDTO);
}
