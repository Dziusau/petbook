package com.dusov.user;

import org.springframework.data.repository.CrudRepository;

import com.dusov.user.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
