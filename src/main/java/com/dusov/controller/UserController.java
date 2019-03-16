package com.dusov.controller;

import com.dusov.entities.User;
import com.dusov.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public @ResponseBody
    String addNewUser (@RequestParam Integer id, @RequestParam String name, @RequestParam(required = false) String surname, @RequestParam(required = false) Integer age, @RequestParam(required = false) String address) {
        User n = new User();
        n.setId(id);
        n.setName(name);
        n.setSurname(surname);
        n.setAge(age);
        n.setAddress(address);
        userRepository.save(n);
        return "Saved";
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/get")
    public @ResponseBody
    Optional<User> getCurrentUser(@RequestParam Integer id){
        return userRepository.findById(id);
    }
    @GetMapping("/delete")
    public @ResponseBody
    String deleteCurrentUser(@RequestParam Integer id){
        userRepository.deleteById(id);
        return "Deleted";
    }
    @GetMapping("/updateName")
    public @ResponseBody
    String updateCurrentUser(@RequestParam String name){
        return "Name updated";
    }
}
