package com.dusov;

import org.springframework.data.repository.CrudRepository;

import com.dusov.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
