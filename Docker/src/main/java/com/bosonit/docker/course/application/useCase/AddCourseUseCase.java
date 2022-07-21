package com.bosonit.docker.course.application.useCase;

import com.bosonit.docker.course.application.port.AddCoursePort;
import com.bosonit.docker.course.domain.Course;
import com.bosonit.docker.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.docker.course.infrastructure.dto.output.CourseOutputDTO;
import com.bosonit.docker.course.infrastructure.repository.CourseRepository;
import com.bosonit.docker.student.domain.Student;
import com.bosonit.docker.student.infrastructure.repository.StudentRepository;
import com.bosonit.docker.exception.NotFoundException;
import com.bosonit.docker.teacher.domain.Teacher;
import com.bosonit.docker.teacher.infrastructure.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddCourseUseCase implements AddCoursePort {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public CourseOutputDTO addCourse(CourseInputDTO courseInputDTO) {
        Course course = new Course(courseInputDTO);
        if (courseInputDTO.getIdTeacher() != null) {
            course.setTeacher(getTeacher(courseInputDTO.getIdTeacher()));
        }
        List<Student> students = getStudentsIds(courseInputDTO.getIdsStudents());
        course.setStudents(students);
        courseRepository.save(course);

        return new CourseOutputDTO(course);
    }

    private Teacher getTeacher(String id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No teacher found with id: " + id));
    }

    private List<Student> getStudentsIds(List<String> ids) {
        List<Student> students = new ArrayList<>();
        if (ids != null) {
            students = studentRepository.findAllById(ids);
            if (ids.size() != students.size()) {
                throw new NotFoundException("No students found with ids: " + ids);
            }
        }

        return students;
    }
}
