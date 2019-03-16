package com.dusov.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dusov.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
