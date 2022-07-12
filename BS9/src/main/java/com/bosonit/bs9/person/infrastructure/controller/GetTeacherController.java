package com.bosonit.bs9.person.infrastructure.controller;

import com.bosonit.bs9.configuration.feign.IFeignServer;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/getTeacher")
public class GetTeacherController {

    @Autowired
    IFeignServer feignServer;

    // Method with RestTemplate
    @GetMapping("/restTemplate/{id}")
    ResponseEntity<TeacherOutputDTO> getTeacherRestTemplate(@PathVariable("id") String id) throws Exception {

        try {
            ResponseEntity<TeacherOutputDTO> teacherOutputDTO = new RestTemplate()
                    .getForEntity("http://localhost:8080/teacher/" + id + "?outputType=full", TeacherOutputDTO.class);
            return ResponseEntity.ok(teacherOutputDTO.getBody());
        } catch (HttpClientErrorException k1) {
            throw new Exception("Http code is not 2xx. The server responded: " + k1.getStatusCode() +
                    " Cause: " + k1.getResponseBodyAsString());
        } catch (RestClientException k2) {
            throw new Exception("The server didn't respond: " + k2.getMessage());
        }
    }

    // Method with Feign
    @GetMapping("/feign/{id}")
    ResponseEntity<TeacherOutputDTO> getTeacherFeign(@PathVariable("id") String id,
            @QueryParam("outputType") String outputType) throws Exception {

        try {
            ResponseEntity<TeacherOutputDTO> teacherOutputDTO = feignServer.callServer(id, outputType);
            return ResponseEntity.ok(teacherOutputDTO.getBody());
        } catch (HttpClientErrorException k1) {
            throw new Exception("Http code is not 2xx. The server responded: " + k1.getStatusCode() +
                    " Cause: " + k1.getResponseBodyAsString());
        } catch (RestClientException k2) {
            throw new Exception("The server didn't respond: " + k2.getMessage());
        }
    }
}
