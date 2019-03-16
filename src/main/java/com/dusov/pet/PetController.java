package com.dusov.pet;

import com.dusov.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @GetMapping("/add")
    public @ResponseBody
    String addNewPet (@RequestParam String name, @RequestParam(required = false) String gender, @RequestParam Integer age) {

        Pet a = new Pet();
        a.setName(name);
        a.setAge(age);
        a.setGender(gender);
        petRepository.save(a);
        return "Saved";
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<Pet> getAllUsers() {
        return petRepository.findAll();
    }
}
