package com.oris.lab11.service;

import com.oris.lab11.mapper.AnimalMapper;
import com.oris.lab11.model.Animal;
import com.oris.lab11.repository.AnimalRepository;

public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService() {
        repository = new AnimalRepository(new AnimalMapper());
    }

    public void save(Animal entity) {
        repository.save(entity);
    }
}