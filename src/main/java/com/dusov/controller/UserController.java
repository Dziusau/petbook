package com.dusov.controller;

import com.dusov.entities.User;
import com.dusov.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public String getCurrentUser(@PathVariable Integer userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "get-user";
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    @GetMapping("/all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "get-allUsers";
    }

    @PostMapping("/add")
    public String addNewUserForm(Model model){
        model.addAttribute("user", new User());
        return "post-user";
    }
    @PostMapping("/post")
    public String addNewUserSubmit(@ModelAttribute User user){
        return "get-user";
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
            userRepository.save(n);
        } else {
            throw new IllegalArgumentException("User not found!");
        }
        return "get-user";
    }
}
