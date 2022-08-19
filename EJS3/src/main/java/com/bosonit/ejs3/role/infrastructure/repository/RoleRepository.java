package com.bosonit.ejs3.role.infrastructure.repository;

import com.bosonit.ejs3.role.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);
}
