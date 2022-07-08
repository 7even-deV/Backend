package com.bosonit.ej3.course.application.useCase;

import com.bosonit.ej3.course.application.port.UpdateCoursePort;
import com.bosonit.ej3.course.domain.Course;
import com.bosonit.ej3.course.infrastructure.dto.input.CourseInputDTO;
import com.bosonit.ej3.course.infrastructure.dto.output.CourseOutputDTO;
import com.bosonit.ej3.course.infrastructure.repository.CourseRepository;
import com.bosonit.ej3.student.domain.Student;
import com.bosonit.ej3.student.infrastructure.repository.StudentRepository;
import com.bosonit.ej3.exception.NotFoundException;
import com.bosonit.ej3.teacher.domain.Teacher;
import com.bosonit.ej3.teacher.infrastructure.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateCourseUseCase implements UpdateCoursePort {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public CourseOutputDTO updateCourse(String id, CourseInputDTO courseInputDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No course found with id: " + id));
        course.update(courseInputDTO);
        if (courseInputDTO.getIdTeacher() != null) {
            course.setTeacher(getTeacher(courseInputDTO.getIdTeacher()));
        }
        List<String> students = new ArrayList<>(
                course.getStudents().stream().map(Student::getIdStudent).toList());
        students.addAll(courseInputDTO.getIdsStudents());
        course.setStudents(getStudentsIds(students.stream().distinct().toList()));
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
