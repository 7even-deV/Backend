package com.bosonit.ej3.person.infrastructure.repository;

import com.bosonit.ej3.person.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByName(String username);
}
