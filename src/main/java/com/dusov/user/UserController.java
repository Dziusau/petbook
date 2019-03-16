package com.dusov.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public @ResponseBody
    String addNewUser (@RequestParam String name, @RequestParam(required = false) String surname, @RequestParam Integer age, @RequestParam String address) {

        User n = new User();
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
}