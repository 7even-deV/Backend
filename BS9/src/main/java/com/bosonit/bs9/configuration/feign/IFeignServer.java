package com.bosonit.bs9.configuration.feign;

import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.QueryParam;

@FeignClient(name = "myFeign", url = "http://localhost:8081")
public interface IFeignServer {

    @GetMapping("/teacher/{id}")
    ResponseEntity<TeacherOutputDTO> callServer(@PathVariable("id") String id,
            @QueryParam("outputType") String outputType) throws Exception;
}
