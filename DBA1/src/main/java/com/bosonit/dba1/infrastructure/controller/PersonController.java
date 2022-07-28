package com.bosonit.dba1.infrastructure.controller;

import com.bosonit.dba1.application.interfaces.IPersonService;
import com.bosonit.dba1.domain.Person;
import com.bosonit.dba1.infrastructure.dto.input.PersonInputDTO;
import com.bosonit.dba1.infrastructure.dto.output.PersonOutputDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    IPersonService iPersonService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    public PersonOutputDTO addPerson(@Valid @RequestBody PersonInputDTO personInputDTO) throws Exception {
        return iPersonService.addPerson(personInputDTO);
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonId(@PathVariable("id") int id) throws Exception {
        return iPersonService.getPersonId(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonOutputDTO> getPersonName(@PathVariable("name") String name) throws Exception {
        return iPersonService.getPersonName(name);
    }

    @GetMapping
    public List<PersonOutputDTO> getPersons() {
        return iPersonService.getPersons();
    }

    @GetMapping("/filter")
    public Page<PersonOutputDTO> getFilteredPersons(@RequestParam Map<String, String> params,
            @RequestParam("page") int page, @PageableDefault Pageable pageable) {
        if (checkParams(params)) {
            return iPersonService.getPersonsPageable(pageable).map(PersonOutputDTO::new);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> queryPerson = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = queryPerson.from(Person.class);
        List<String> fields = List.of("username", "name", "surname", "created_date");
        List<Predicate> predicates = new ArrayList<>();
        params.forEach((field, value) -> {
            if (fields.contains(field)) {
                if (field.equals("created_date")) {
                    Date creationDate = parseDate(value);
                    if (params.get("after_before") == null) {
                        predicates.add(criteriaBuilder.equal(personRoot.get(field), creationDate));
                    } else {
                        if (params.get("after_before").equals("after")) {
                            predicates.add(criteriaBuilder.greaterThan(personRoot.get(field), creationDate));
                        } else if (params.get("after_before").equals("before")) {
                            predicates.add(criteriaBuilder.lessThan(personRoot.get(field), creationDate));
                        }
                    }
                }
            } else {
                predicates.add(criteriaBuilder.lessThan(personRoot.get(field), value));
            }
        });
        queryPerson.select(personRoot).where(predicates.toArray(new Predicate[predicates.size()]));
        List<PersonOutputDTO> personsList = entityManager.createQuery(queryPerson)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultStream().map(PersonOutputDTO::new)
                .collect(Collectors.toList());
        CriteriaQuery<Long> queryCount = criteriaBuilder.createQuery(Long.class);
        Root<Person> rootCount = queryCount.from(Person.class);
        queryCount.select(criteriaBuilder.count(rootCount))
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        Long count = entityManager.createQuery(queryCount).getSingleResult();
        return new PageImpl<PersonOutputDTO>(personsList, pageable, count);
    }

    private boolean checkParams(Map<String, String> params) {
        params.remove("size");
        params.remove("page");
        return params.isEmpty();
    }

    private Date parseDate(String value) {
        Date creationDate;
        try {
            creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
        return creationDate;
    }
}
