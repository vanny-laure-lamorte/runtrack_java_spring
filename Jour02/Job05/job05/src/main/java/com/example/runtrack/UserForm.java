package com.example.runtrack;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserForm {

    @NotEmpty(message = "Le nom ne peut pas être vide")
    private String nom;

    @NotEmpty(message = "Le prénom ne peut pas être vide")
    private String prenom;

    @NotNull(message = "L'âge ne peut pas être nul")
    @Min(value = 18, message = "L'âge doit être supérieur ou égal à 18 ans")
    private Integer age;

    @NotEmpty(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotEmpty(message = "Le numéro de téléphone ne peut pas être vide")
    @Size(min = 10, max = 15, message = "Le numéro de téléphone doit avoir entre 10 et 15 caractères")
    private String numero;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
