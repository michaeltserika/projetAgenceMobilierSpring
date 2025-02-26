package com.agence.immobiliere.repository;

import com.agence.immobiliere.model.Bien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BienRepository extends JpaRepository<Bien, Integer> {
}