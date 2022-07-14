package com.bosonit.bs12.course.application.port;

import com.bosonit.bs12.course.infrastructure.dto.output.CourseOutputDTO;

import java.util.List;

public interface GetCoursePort {
    CourseOutputDTO getCourseById(String id);
    List<CourseOutputDTO> getCourseStudentById(String idStudent);
}
