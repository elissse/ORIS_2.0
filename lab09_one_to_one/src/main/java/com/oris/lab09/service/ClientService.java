package com.oris.lab09.service;

import com.oris.lab09.model.Client;
import com.oris.lab09.repository.ClientRepository;

import java.util.List;

public class ClientService {

    private final ClientRepository repository;

    public ClientService() {
        repository = new ClientRepository();
    }

    public Client save(Client client) {
        return repository.addClient(client);
    }

    public List<Client> getAll() {
        return repository.getAll();
    }

    public Client findById(Long id) {
        return repository.findById(id);
    }
}
