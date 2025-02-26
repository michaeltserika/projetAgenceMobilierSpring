package com.agence.immobiliere.service;

import com.agence.immobiliere.model.Client;
import com.agence.immobiliere.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }
}