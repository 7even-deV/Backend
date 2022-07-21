package com.bosonit.docker.student.infrastructure.repository;

import com.bosonit.docker.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
