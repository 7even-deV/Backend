package com.bosonit.bs9.course.application.port;

import com.bosonit.bs9.course.infrastructure.dto.output.CourseOutputDTO;

import java.util.List;

public interface GetCoursePort {
    CourseOutputDTO getCourseById(String id);
    List<CourseOutputDTO> getCourseStudentById(String idStudent);
}
