package com.bosonit.bs12.student.application.useCase;

import com.bosonit.bs12.course.domain.Course;
import com.bosonit.bs12.course.infrastructure.repository.CourseRepository;
import com.bosonit.bs12.student.application.port.AddStudentPort;
import com.bosonit.bs12.student.domain.Student;
import com.bosonit.bs12.student.infrastructure.dto.input.StudentInputDTO;
import com.bosonit.bs12.student.infrastructure.dto.output.StudentOutputDTO;
import com.bosonit.bs12.student.infrastructure.repository.StudentRepository;
import com.bosonit.bs12.exception.NotFoundException;
import com.bosonit.bs12.person.domain.Person;
import com.bosonit.bs12.person.infrastructure.repository.PersonRepository;
import com.bosonit.bs12.teacher.domain.Teacher;
import com.bosonit.bs12.teacher.infrastructure.repository.TeacherRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddStudentUseCase implements AddStudentPort {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public StudentOutputDTO addStudent(@NotNull StudentInputDTO studentInputDTO) {
        Person person = personRepository.findById(studentInputDTO.getIdPerson())
                .orElseThrow(() -> new NotFoundException("No person found with id: " + studentInputDTO.getIdPerson()));
        Teacher teacher = null;
        if (studentInputDTO.getIdTeacher() != null) {
            teacher = teacherRepository.findById(studentInputDTO.getIdTeacher())
                    .orElseThrow(() -> new NotFoundException("No teacher found with id: " + studentInputDTO.getIdPerson()));
        }
        List<Course> courses = getCoursesIds(studentInputDTO.getCourses());
        Student student = new Student(studentInputDTO);
        student.setPerson(person);
        student.setTeacher(teacher);
        student.setCourses(courses);
        courses.forEach(course -> course.addStudent(student));
        studentRepository.save(student);

        return new StudentOutputDTO(student);
    }

    @Override
    public StudentOutputDTO addCourses(String idStudent, List<String> coursesInsert) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new NotFoundException("No student found with id: " + idStudent));
        List<String> idsCourses = student.getCourses().stream().map(Course::getIdCourse)
                .collect(Collectors.toList());
        idsCourses.addAll(coursesInsert);
        List<Course> courses = getCoursesIds(idsCourses.stream().distinct().collect(Collectors.toList()));
        student.setCourses(courses);
        courses.forEach(course -> course.addStudent(student));
        studentRepository.save(student);

        return new StudentOutputDTO(student);
    }

    private List<Course> getCoursesIds(List<String> ids) {
        List<Course> courses = new ArrayList<>();
        if (ids != null) {
            courses = courseRepository.findAllById(ids);
            if (ids.size() != courses.size()) {
                throw new NotFoundException("No courses found with ids: " + ids);
            }
        }

        return courses;
    }
}
