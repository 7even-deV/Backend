package com.bosonit.ejs3.role.infrastructure.controller;

import com.bosonit.ejs3.role.application.service.RoleService;
import com.bosonit.ejs3.role.domain.Role;
import com.bosonit.ejs3.role.infrastructure.dto.RoleDTO;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role addRole(@RequestBody RoleDTO roleDTO) {
        return roleService.addRole(roleDTO);
    }

    @PostMapping(value = "/addroletouser", consumes = { "multipart/form-data" })
    public void addRoleToUser(@ModelAttribute RoleToUserForm form) {
        roleService.addRoleToPerson(form.getUsername(), form.getRole());
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable("id") String idRole) {
        try {
            roleService.deleteRole(idRole);
            return "The role with idRole " + idRole + " has been successfully eliminated.";
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }

    @Data
    static class RoleToUserForm {
        private String username;
        private String role;
    }
}
