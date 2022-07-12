package com.bosonit.bs9.configuration.feign;

import com.bosonit.bs9.teacher.application.port.GetTeacherPort;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements IFeignServer {

    @Autowired
    GetTeacherPort getTeacherPort;

    public ResponseEntity<TeacherOutputDTO> callServer(String id, String outputType) throws Exception {

        return ResponseEntity.ok(getTeacherPort.getTeacherId(id, outputType).getBody());
    }
}
