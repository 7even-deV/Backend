package com.bosonit.ejs3.role.application.service;

import com.bosonit.ejs3.role.domain.Role;
import com.bosonit.ejs3.role.infrastructure.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    Role addRole(RoleDTO roleDTO);
    void addRoleToPerson(String username, String roleName);
    List<Role> getAllRoles();
    void deleteRole(String idRole);
}
