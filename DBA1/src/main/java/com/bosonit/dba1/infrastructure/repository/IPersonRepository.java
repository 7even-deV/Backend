package com.bosonit.dba1.infrastructure.repository;

import com.bosonit.dba1.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String username);
}
