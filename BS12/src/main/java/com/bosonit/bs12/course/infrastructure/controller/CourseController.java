package com.bosonit.bs12.course.infrastructure.controller;

import com.bosonit.bs12.course.application.port.AddCoursePort;
import com.bosonit.bs12.course.application.port.DeleteCoursePort;
import com.bosonit.bs12.course.application.port.GetCoursePort;
import com.bosonit.bs12.course.application.port.UpdateCoursePort;
import com.bosonit.bs12.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.bs12.course.infrastructure.dto.output.CourseOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    AddCoursePort addCoursePort;
    @Autowired
    DeleteCoursePort deleteCoursePort;
    @Autowired
    GetCoursePort getCoursePort;
    @Autowired
    UpdateCoursePort updateCoursePort;

    @PostMapping
    public CourseOutputDTO addCourse(@Valid @RequestBody CourseInputDTO courseInputDTO) {
        return addCoursePort.addCourse(courseInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") String id) {
        deleteCoursePort.deleteCourse(id);
    }

    @GetMapping("/{id}")
    public CourseOutputDTO getCourse(@PathVariable("id") String id) {
        return getCoursePort.getCourseById(id);
    }

    @GetMapping("/courses/{id}")
    public List<CourseOutputDTO> getCourseStudentById(@PathVariable("id") String idStudent) {
        return getCoursePort.getCourseStudentById(idStudent);
    }

    @PutMapping("/{id}")
    public CourseOutputDTO updateCourse(@PathVariable("id") String id,
            @RequestBody CourseInputDTO courseInputDTO) {
        return updateCoursePort.updateCourse(id, courseInputDTO);
    }
}
