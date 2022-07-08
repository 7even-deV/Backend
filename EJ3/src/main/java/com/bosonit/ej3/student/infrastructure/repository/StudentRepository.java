package com.bosonit.ej3.student.infrastructure.repository;

import com.bosonit.ej3.student.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
