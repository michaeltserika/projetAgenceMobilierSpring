package com.agence.immobiliere.repository;

import com.agence.immobiliere.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
}