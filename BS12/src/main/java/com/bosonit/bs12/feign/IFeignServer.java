package com.bosonit.bs12.feign;

import com.bosonit.bs12.teacher.infrastructure.dto.output.TeacherOutputDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignTest", url = "http://localhost:8080")
public interface IFeignServer {

    @GetMapping("/teacher/{id}")
    public TeacherOutputDTO getTeacher(@PathVariable("id") String idTeacher);
}
