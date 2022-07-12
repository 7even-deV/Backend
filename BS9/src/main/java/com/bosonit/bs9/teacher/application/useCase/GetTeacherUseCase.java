package com.bosonit.bs9.teacher.application.useCase;

import com.bosonit.bs9.exception.NotFoundException;
import com.bosonit.bs9.teacher.application.port.GetTeacherPort;
import com.bosonit.bs9.teacher.domain.Teacher;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherFullOutputDTO;
import com.bosonit.bs9.teacher.infrastructure.dto.output.TeacherOutputDTO;
import com.bosonit.bs9.teacher.infrastructure.repository.TeacherRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTeacherUseCase implements GetTeacherPort {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public TeacherOutputDTO getTeacherId(String id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No teacher found with id: " + id));

        return new TeacherOutputDTO(teacher);
    }

    @Override
    public ResponseEntity<TeacherOutputDTO> getTeacherId(String id, String outputType) throws Exception {
        return null;
    }

    public TeacherFullOutputDTO findTeacherId(String id, String outputType) throws Exception {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
            throw new NotFoundException("No teacher found with id: " + id);
        }
        if (outputType.equals("full")) {
            return modelMapper.map(teacher, TeacherFullOutputDTO.class);
        } else {
            return (TeacherFullOutputDTO) modelMapper.map(teacher, TeacherOutputDTO.class);
        }
    }
}
