package com.bosonit.backbusiness.client.infrastructure.repository;

import com.bosonit.backbusiness.client.domain.Client;

import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @NotFound
    Client findByEmail(String email);
}
