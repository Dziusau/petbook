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
    String addNewPet (@RequestParam String name, @RequestParam(required = false) String gender,
                      @RequestParam(required = false) Integer age) {
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
    @GetMapping("/{petId}")
    public @ResponseBody
    Optional<Pet> getCurrentPet(@PathVariable Integer petId){
        return petRepository.findById(petId);
    }
    @DeleteMapping("/{petId}")
    public @ResponseBody
    String deleteCurrentUser(@PathVariable Integer petId){
        petRepository.deleteById(petId);
        return "Deleted";
    }
    @PutMapping("/{petId})")
    public String updateCurrentPet(@PathVariable Integer petId, @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String gender, @RequestParam(required = false) Integer age){
        Optional<Pet> pet = petRepository.findById(petId);
        if (pet.isPresent()){
            Pet n = new Pet();
            if (name != null){
                n.setName(name);
            }
            if (age != null){
                n.setAge(age);
            }
            if (gender != null){
                n.setGender(gender);
            }
            petRepository.save(n);
        }
        return "Saved";
    }
}
