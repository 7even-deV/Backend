package com.bosonit.bs9.teacher.application.port;

import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.http.ResponseEntity;

public interface GetTeacherPort {
    TeacherOutputDTO getTeacherId(String id);
    ResponseEntity<TeacherOutputDTO> getTeacherId(String id, String outputType) throws Exception;
}
