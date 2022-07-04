package com.bosonit.rs1.infrastructure.crud;

import com.bosonit.rs1.application.interfaces.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class DeleteController {

    @Autowired
    IPersonService iPersonService;

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        if (iPersonService.removePerson(id))
            return "The person with ID " + id + " has been successfully eliminated.";

        return "The person with ID " + id + " could not be deleted, because it was in the list.";
    }
}
