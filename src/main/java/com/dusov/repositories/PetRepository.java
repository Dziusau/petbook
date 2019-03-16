package com.dusov.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dusov.entities.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {
}
