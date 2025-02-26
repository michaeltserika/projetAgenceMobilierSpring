package com.agence.immobiliere.service;

import com.agence.immobiliere.model.Proprietaire;
import com.agence.immobiliere.repository.ProprietaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietaireService {
    @Autowired
    private ProprietaireRepository proprietaireRepository;

    public Proprietaire save(Proprietaire proprietaire) {
        return proprietaireRepository.save(proprietaire);
    }

    public List<Proprietaire> findAll() {
        return proprietaireRepository.findAll();
    }

    public Proprietaire findById(Integer id) {
        return proprietaireRepository.findById(id).orElse(null);
    }

    public void delete(Proprietaire proprietaire) {
        proprietaireRepository.delete(proprietaire);
    }
}