package com.example.runtrack;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/view")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "view";
    }

    @PostMapping("/submit")
    public String handleFormSubmission(
        @Valid @ModelAttribute UserForm userForm,
        BindingResult result,
        Model model) {

        if (result.hasErrors()) {
            return "view";
        }

        String welcomeMessage = "Bienvenue " + userForm.getPrenom() + " " + userForm.getNom() + " ! Vous êtes inscrit avec l'email : ";


        model.addAttribute("welcomeMessage", "");

        return "view";
    }
}
