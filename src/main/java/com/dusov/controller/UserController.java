package com.dusov.controller;

import com.dusov.entities.User;
import com.dusov.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public @ResponseBody
    String addNewUser(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                      @RequestParam(required = false) Integer age, @RequestParam(required = false) String address) {
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
    public String getCurrentUser(@PathVariable Integer userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
          if (user.isPresent()) {
            model.addAttribute("user", user.get());
          } else {
              throw new IllegalArgumentException("User not found!");
          }
        return "user";
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
    public String updateCurrentUser(@PathVariable Integer userId, @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String surname, @RequestParam(required = false) Integer age,
                                    @RequestParam(required = false) String address, Model model){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User n = user.get();
            if (name != null) {
                n.setName(name);
            }
            if (surname != null){
            n.setSurname(surname);
            }
            if (age != null) {
                n.setAge(age);
            }
            if (address != null) {
                n.setAddress(address);
            }
        }
        return "user";
    }
}
