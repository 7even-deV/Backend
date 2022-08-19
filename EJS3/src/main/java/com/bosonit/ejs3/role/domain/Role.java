package com.bosonit.ejs3.role.domain;

import com.bosonit.ejs3.SequenceIdGenerator;
import com.bosonit.ejs3.role.infrastructure.dto.RoleDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "Roles")
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idRole")
    @GenericGenerator(name = "idRole", strategy = "com.bosonit.ejs3.SequenceIdGenerator", parameters = {
            @Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "r"),
            @Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String idRole;
    private String roleName;

    public Role(RoleDTO roleDTO) {
        setIdRole(roleDTO.getIdRole());
        setRoleName(roleDTO.getRoleName());
    }
}
