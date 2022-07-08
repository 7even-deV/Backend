package com.bosonit.ej3.course.application.port;

import com.bosonit.ej3.course.infrastructure.dto.output.CourseOutputDTO;

import java.util.List;

public interface GetCoursePort {
    CourseOutputDTO getCourseById(String id);
    List<CourseOutputDTO> getCourseStudentById(String idStudent);
}
