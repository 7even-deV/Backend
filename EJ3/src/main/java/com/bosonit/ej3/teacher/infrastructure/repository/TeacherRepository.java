package com.bosonit.ej3.teacher.infrastructure.repository;

import com.bosonit.ej3.teacher.domain.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
