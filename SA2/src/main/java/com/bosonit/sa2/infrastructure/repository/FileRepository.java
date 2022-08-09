package com.bosonit.sa2.infrastructure.repository;

import com.bosonit.sa2.domain.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    Optional<File> findByFileName(String fileName);
}
