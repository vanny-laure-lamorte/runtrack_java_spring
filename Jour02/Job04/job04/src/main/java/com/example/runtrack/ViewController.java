package com.example.runtrack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/view")
    public String showForm(Model model) {
        model.addAttribute("welcomeMessage", "");
        return "view";
    }

    @PostMapping("/submit")
    public String handleFormSubmission(
        @RequestParam String nom,
        @RequestParam String prenom,
        @RequestParam int age,
        @RequestParam String email,
        @RequestParam String numero,
        Model model) {

        String welcomeMessage = "Bienvenue " + prenom + " " + nom + " ! Vous êtes inscrit avec l'email : " + email;

        model.addAttribute("welcomeMessage", welcomeMessage);

        return "view";
    }
}
