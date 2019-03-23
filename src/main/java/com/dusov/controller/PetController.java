package com.dusov.controller;

import com.dusov.entities.Pet;
import com.dusov.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @GetMapping("/add")
    public @ResponseBody
    String addNewPet (@RequestParam String name, @RequestParam(required = false) String gender, @RequestParam(required = false) Integer age) {
        Pet a = new Pet();
        a.setName(name);
        a.setAge(age);
        a.setGender(gender);
        petRepository.save(a);
        return "Saved";
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<Pet> getAllPets() {
        return petRepository.findAll();
    }
    @GetMapping("/petId")
    public @ResponseBody
    Optional<Pet> getCurrentPet(@PathVariable Integer petId){
        return petRepository.findById(petId);
    }
    @DeleteMapping("/petId")
    public @ResponseBody
    String deleteCurrentUser(@PathVariable Integer petId){
        petRepository.deleteById(petId);
        return "Deleted";
    }
}
