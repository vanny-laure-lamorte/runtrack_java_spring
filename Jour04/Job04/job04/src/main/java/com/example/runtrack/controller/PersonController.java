package com.example.runtrack.controller;

import com.example.runtrack.model.Person;
import com.example.runtrack.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/person-list")
    public String listUsers(Model model, Principal principal) {
        Person loggedInUser = personRepository.findByUsername(principal.getName());
        model.addAttribute("CoInfo", loggedInUser);
        model.addAttribute("persons", personRepository.findAll());
    return "person-list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        model.addAttribute("person", person);
        return "edit-user";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedUser(@PathVariable Integer id, @ModelAttribute Person personInput, Model model, Principal principal) {
        Person loggedInUser = personRepository.findByUsername(principal.getName());
        if (!"ROLE_ADMIN".equals(loggedInUser.getRole())) {
            return "redirect:/person-list?unauthorized";
        }

        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        existingPerson.setUsername(personInput.getUsername());
        existingPerson.setRole(personInput.getRole());

        personRepository.save(existingPerson);
        return "redirect:/person-list";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Principal principal) {
        Person loggedInUser = personRepository.findByUsername(principal.getName());

        System.out.println("Logged in user: " + loggedInUser.getUsername());

        if ("ROLE_ADMIN".equals(loggedInUser.getRole())) {
            if (personRepository.existsById(id)) {
                personRepository.deleteById(id);
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Utilisateur non trouvé.");
            }
        } else {
            System.out.println("Tentative de suppression non autorisée !");
        }

        return "redirect:/person-list";
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
