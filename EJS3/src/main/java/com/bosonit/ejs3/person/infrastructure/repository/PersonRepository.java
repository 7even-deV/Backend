package com.bosonit.ejs3.person.infrastructure.repository;

import com.bosonit.ejs3.person.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Person findByUsername(String username);
}
