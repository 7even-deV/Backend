package com.bosonit.bs9.teacher.infrastructure.repository;

import com.bosonit.bs9.teacher.domain.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
