package com.bosonit.bs8.content.infrastructure.repository;

import com.bosonit.bs8.content.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
}
