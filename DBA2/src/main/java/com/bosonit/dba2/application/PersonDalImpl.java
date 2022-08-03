package com.bosonit.dba2.application;

import com.bosonit.dba2.domain.Person;

import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDalImpl implements PersonDal {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Person addPerson(Person person) {
        return mongoTemplate.save(person);
    }

    @Override
    public Person findById(String id) {
        return mongoTemplate.findById(id, Person.class);
    }

    @Override
    public List<Person> findByName(String name) {
        Query query = new Query().addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Person.class);
    }

    @Override
    public List<Person> getAll() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public void deletePerson(Person person) {
        mongoTemplate.remove(person);
    }

    @Override
    public Person updatePerson(String id, Person person) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        if (person.getUsername() != null) {
            update.set("username", person.getUsername());
        }
        if (person.getPassword() != null) {
            update.set("password", person.getPassword());
        }
        if (person.getName() != null) {
            update.set("name", person.getName());
        }
        if (person.getSurname() != null) {
            update.set("surname", person.getSurname());
        }

        UpdateResult result = mongoTemplate.updateFirst(query, update, Person.class);
        return mongoTemplate.findById(id, Person.class);
    }
}
