package com.bosonit.ejs3.role.infrastructure.dto;

import com.bosonit.ejs3.role.domain.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {

    private String idRole;
    private String roleName;

    public RoleDTO(Role role) {
        setIdRole(role.getIdRole());
        setRoleName(role.getRoleName());
    }

    public RoleDTO(String idRole, String roleName) {
        setIdRole(idRole);
        setRoleName(roleName);
    }
}
