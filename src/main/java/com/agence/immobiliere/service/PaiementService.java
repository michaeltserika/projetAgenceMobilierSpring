package com.agence.immobiliere.service;

import com.agence.immobiliere.model.Paiement;
import com.agence.immobiliere.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public Paiement findById(Integer id) {
        return paiementRepository.findById(id).orElse(null);
    }

    public void delete(Paiement paiement) {
        paiementRepository.delete(paiement);
    }
}