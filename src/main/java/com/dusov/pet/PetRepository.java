package com.dusov.pet;

import org.springframework.data.repository.CrudRepository;

import com.dusov.user.User;

public interface PetRepository extends CrudRepository<User, Integer> {
}
