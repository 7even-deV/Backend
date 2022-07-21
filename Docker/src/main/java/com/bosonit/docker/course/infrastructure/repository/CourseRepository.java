package com.bosonit.docker.course.infrastructure.repository;

import com.bosonit.docker.course.domain.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
