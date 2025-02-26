package com.agence.immobiliere.repository;

import com.agence.immobiliere.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Integer> {
}