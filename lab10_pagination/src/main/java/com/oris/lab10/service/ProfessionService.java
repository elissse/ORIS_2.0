package com.oris.lab10.service;

import com.oris.lab10.model.Profession;
import com.oris.lab10.repository.ProfessionRepository;

import java.util.List;

public class ProfessionService {

    private final ProfessionRepository repository;

    public ProfessionService() {
        repository = new ProfessionRepository();
    }

    public List<Profession> getAllByName(String name, int page) {
        return repository.getAllByName(name, page);
    }

    public int countRows() {
        return repository.countRows();
    }
}