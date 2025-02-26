package com.agence.immobiliere.repository;

import com.agence.immobiliere.model.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietaireRepository extends JpaRepository<Proprietaire, Integer> {
}