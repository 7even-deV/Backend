package com.bosonit.ej3.teacher.infrastructure.controller;

import com.bosonit.ej3.teacher.application.port.AddTeacherPort;
import com.bosonit.ej3.teacher.application.port.DeleteTeacherPort;
import com.bosonit.ej3.teacher.application.port.GetTeacherPort;
import com.bosonit.ej3.teacher.application.port.UpdateTeacherPort;
import com.bosonit.ej3.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.ej3.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    AddTeacherPort addTeacherPort;
    @Autowired
    DeleteTeacherPort deleteTeacherPort;
    @Autowired
    GetTeacherPort getTeacherPort;
    @Autowired
    UpdateTeacherPort updateTeacherPort;

    @PostMapping
    public TeacherOutputDTO addTeacher(@Valid @RequestBody TeacherInputDTO teacherInputDTO) {
        return addTeacherPort.addTeacher(teacherInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") String id) {
        deleteTeacherPort.deleteTeacher(id);
    }

    @GetMapping("/{id}")
    public TeacherOutputDTO getTeacherId(@PathVariable("id") String id) {
        return getTeacherPort.getTeacherId(id);
    }

    @PutMapping("/{id}")
    public TeacherOutputDTO updateTeacher(@PathVariable("id") String id, @RequestBody TeacherInputDTO teacherInputDTO) {
        return updateTeacherPort.updateTeacher(id, teacherInputDTO);
    }
}
