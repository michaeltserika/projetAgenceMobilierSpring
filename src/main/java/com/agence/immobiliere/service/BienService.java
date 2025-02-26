package com.agence.immobiliere.service;

import com.agence.immobiliere.model.Bien;
import com.agence.immobiliere.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {
    @Autowired
    private BienRepository bienRepository;

    public Bien save(Bien bien) {
        return bienRepository.save(bien);
    }

    public List<Bien> findAll() {
        return bienRepository.findAll();
    }

    public Bien findById(Integer id) {
        return bienRepository.findById(id).orElse(null);
    }

    public void delete(Bien bien) {
        bienRepository.delete(bien);
    }
}