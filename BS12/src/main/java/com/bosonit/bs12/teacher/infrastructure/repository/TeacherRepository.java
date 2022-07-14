package com.bosonit.bs12.teacher.infrastructure.repository;

import com.bosonit.bs12.teacher.domain.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
