package com.dusov.pet;

import org.springframework.data.repository.CrudRepository;

import com.dusov.pet.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {
}
