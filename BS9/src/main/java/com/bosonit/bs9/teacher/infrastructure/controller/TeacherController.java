package com.bosonit.bs9.teacher.infrastructure.controller;

import com.bosonit.bs9.teacher.application.port.AddTeacherPort;
import com.bosonit.bs9.teacher.application.port.DeleteTeacherPort;
import com.bosonit.bs9.teacher.application.port.GetTeacherPort;
import com.bosonit.bs9.teacher.application.port.UpdateTeacherPort;
import com.bosonit.bs9.teacher.infrastructure.dto.input.TeacherInputDTO;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

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
    public TeacherOutputDTO addTeacher(@RequestBody TeacherInputDTO teacherInputDTO) {
        return addTeacherPort.addTeacher(teacherInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") String id) {
        deleteTeacherPort.deleteTeacher(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutputDTO> getTeacherId(@PathVariable("id") String id,
            @QueryParam("outputType") String outputType) throws Exception {
        return getTeacherPort.getTeacherId(id, outputType);
    }

    @PutMapping("/{id}")
    public TeacherOutputDTO updateTeacher(@PathVariable("id") String id, @RequestBody TeacherInputDTO teacherInputDTO) {
        return updateTeacherPort.updateTeacher(id, teacherInputDTO);
    }
}
