package com.agence.immobiliere.repository;

import com.agence.immobiliere.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}