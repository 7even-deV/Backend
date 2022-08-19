package com.bosonit.ejs3.unit;

import com.bosonit.ejs3.role.application.impl.RoleServiceImpl;
import com.bosonit.ejs3.role.domain.Role;
import com.bosonit.ejs3.role.infrastructure.dto.RoleDTO;
import com.bosonit.ejs3.role.infrastructure.repository.RoleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
@DisplayName("UnitTest - RoleServiceTest")
class RoleServiceTest {

    private final Role role = new Role(new RoleDTO(null, "VIPTest"));

    private final List<Role> roles = List.of(role);

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        roleRepository.save(role);
    }

    @Test
    @DisplayName("addRoleTest")
    void addRoleTest() {
        Role roleTest = new Role(new RoleDTO(null, "nameTest"));
        RoleDTO roleDTOTest = new RoleDTO(null, "nameTest");
        when(roleRepository.save(Mockito.any(Role.class))).thenReturn(roleTest);
        assertEquals(roleTest.getRoleName(), roleService.addRole(roleDTOTest).getRoleName());
    }

    @Test
    @DisplayName("getAllRolesTest")
    void getAllRolesTest() {
        when(roleRepository.findAll()).thenReturn(roles);
        assertEquals(roles, roleService.getAllRoles());
    }

    @Test
    @DisplayName("deleteRoleByIdTest")
    void deleteRoleByIdTest() {
        lenient().when(roleRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(new Role()));
        roleService.deleteRole("r-00003");
        verify(roleRepository).deleteById(Mockito.any(String.class));
    }
}
