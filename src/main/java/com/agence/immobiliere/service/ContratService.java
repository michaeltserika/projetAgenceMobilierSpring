package com.agence.immobiliere.service;

import com.agence.immobiliere.model.Contrat;
import com.agence.immobiliere.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratService {
    @Autowired
    private ContratRepository contratRepository;

    public Contrat save(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    public Contrat findById(Integer id) {
        return contratRepository.findById(id).orElse(null);
    }

    public void delete(Contrat contrat) {
        contratRepository.delete(contrat);
    }
}