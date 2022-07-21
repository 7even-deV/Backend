package com.bosonit.docker.teacher.infrastructure.repository;

import com.bosonit.docker.teacher.domain.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
