package com.bosonit.bs12.student.application.useCase;

import com.bosonit.bs12.course.domain.Course;
import com.bosonit.bs12.course.infrastructure.repository.CourseRepository;
import com.bosonit.bs12.student.application.port.DeleteStudentPort;
import com.bosonit.bs12.student.domain.Student;
import com.bosonit.bs12.student.infrastructure.dto.output.StudentOutputDTO;
import com.bosonit.bs12.student.infrastructure.repository.StudentRepository;
import com.bosonit.bs12.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteStudentUseCase implements DeleteStudentPort {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentOutputDTO deleteCourses(String idStudent, List<String> coursesDelete) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new NotFoundException("No student found with id: " + idStudent));
        List<Course> courses = getCoursesIds(coursesDelete);
        student.getCourses().removeAll(courses);
        courses.forEach(course -> course.deleteStudent(student));
        studentRepository.save(student);

        return new StudentOutputDTO(student);
    }

    private List<Course> getCoursesIds(List<String> ids) {
        List<Course> courses = new ArrayList<>();
        if (ids != null) {
            courses = courseRepository.findAllById(ids);
            if (ids.size() != courses.size()) {
                throw new NotFoundException("No courses found with id: " + ids);
            }
        }

        return courses;
    }
}
