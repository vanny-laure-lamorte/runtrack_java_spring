package com.example.runtrack.controller;

import com.example.runtrack.model.Person;
import com.example.runtrack.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String confirmPassword,
        Model model
        ) {
        System.out.println("Register endpoint hit!");
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Les mots de passe ne correspondent pas.");
            return "register";
        }

        if (personRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Nom d'utilisateur déjà pris.");
            return "register";
        }

        Person person = new Person();
        person.setUsername(username);
        person.setPassword(passwordEncoder.encode(password));
        person.setRole("USER");

        System.out.println("Saving user: " + username);
        personRepository.save(person);
        System.out.println("User saved.");

        return "redirect:/login?success";
    }
}
