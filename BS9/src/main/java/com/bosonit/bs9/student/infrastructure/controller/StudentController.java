package com.bosonit.bs9.student.infrastructure.controller;

import com.bosonit.bs9.student.application.port.AddStudentPort;
import com.bosonit.bs9.student.application.port.DeleteStudentPort;
import com.bosonit.bs9.student.application.port.GetStudentPort;
import com.bosonit.bs9.student.application.port.UpdateStudentPort;
import com.bosonit.bs9.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.bs9.student.infrastructure.dto.output.StudentFullOutputDTO;
import com.bosonit.bs9.student.infrastructure.dto.output.StudentOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    AddStudentPort addStudentPort;
    @Autowired
    DeleteStudentPort deleteStudentPort;
    @Autowired
    GetStudentPort getStudentPort;
    @Autowired
    UpdateStudentPort updateStudentPort;

    @PostMapping
    public StudentOutputDTO addStudent(@Valid @RequestBody StudentInputDTO studentInputDTO) {
        return addStudentPort.addStudent(studentInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        deleteStudentPort.deleteStudent(id);
    }

    @GetMapping("/{id}")
    public StudentOutputDTO getStudentId(@PathVariable(name = "id") String id,
            @RequestParam(name = "outputType", defaultValue = "simple") String outputType) {
        if (outputType.equals("full")) {
            return new StudentFullOutputDTO(getStudentPort.getStudentId(id));
        } else {
            return new StudentOutputDTO(getStudentPort.getStudentId(id));
        }
    }

    @PutMapping("/{id}")
    public StudentOutputDTO updateStudent(@PathVariable("id") String id,
            @RequestBody StudentInputDTO studentInputDTO) {
        return updateStudentPort.updateStudent(id, studentInputDTO);
    }

    @PutMapping("/courseAdd/{id}")
    public StudentOutputDTO addCourse(@PathVariable("id") String idStudent,
            @RequestBody List<String> courses) {
        return addStudentPort.addCourses(idStudent, courses);
    }

    @PutMapping("/courseDelete/{id}")
    public StudentOutputDTO deleteCourse(@PathVariable("id") String idStudent,
            @RequestBody List<String> courses) {
        return deleteStudentPort.deleteCourses(idStudent, courses);
    }
}
