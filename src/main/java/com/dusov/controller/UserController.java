package com.dusov.controller;

import com.dusov.entities.User;
import com.dusov.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam(required = false) String surname, @RequestParam(required = false) Integer age, @RequestParam(required = false) String address) {
        User n = new User();
        n.setName(name);
        n.setSurname(surname);
        n.setAge(age);
        n.setAddress(address);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public @ResponseBody
    Optional<User> getCurrentUser(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public @ResponseBody
    String deleteCurrentUser(@PathVariable Integer userId) {
        try {
            userRepository.deleteById(userId);
            return "Deleted";
        } catch (Exception ex) {
            return "User not found for this id";
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @Valid @RequestBody(required = false) User userDetails){
        User n = userRepository.findById(userId);
        n.setName(userDetails.getName());
        n.setSurname(userDetails.getSurname());
        n.setAge(userDetails.getAge());
        n.setAddress(userDetails.getAddress());
        final User updatedUser = userRepository.save(n);
        return ResponseEntity.ok(updatedUser);
    }
}
