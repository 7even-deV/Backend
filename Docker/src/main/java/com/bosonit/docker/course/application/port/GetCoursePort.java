package com.bosonit.docker.course.application.port;

import com.bosonit.docker.course.infrastructure.dto.output.CourseOutputDTO;

import java.util.List;

public interface GetCoursePort {
    CourseOutputDTO getCourseById(String id);
    List<CourseOutputDTO> getCourseStudentById(String idStudent);
}
