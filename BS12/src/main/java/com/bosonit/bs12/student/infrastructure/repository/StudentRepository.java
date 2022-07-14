package com.bosonit.bs12.student.infrastructure.repository;

import com.bosonit.bs12.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
