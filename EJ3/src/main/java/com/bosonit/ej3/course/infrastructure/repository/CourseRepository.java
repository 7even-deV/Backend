package com.bosonit.ej3.course.infrastructure.repository;

import com.bosonit.ej3.course.domain.Course;

import com.bosonit.ej3.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
