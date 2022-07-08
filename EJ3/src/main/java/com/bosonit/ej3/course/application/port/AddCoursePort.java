package com.bosonit.ej3.course.application.port;

import com.bosonit.ej3.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.ej3.course.infrastructure.dto.output.CourseOutputDTO;

public interface AddCoursePort {
    CourseOutputDTO addCourse(CourseInputDTO courseInputDTO);
}
