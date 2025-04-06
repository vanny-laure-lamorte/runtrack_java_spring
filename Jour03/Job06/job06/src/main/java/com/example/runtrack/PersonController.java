package com.example.runtrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public String getAllPersons(Model model) {
        List<Person> persons = personService.getAllPersons();
        model.addAttribute("persons", persons);
        return "personDisplay";
    }

    @GetMapping("/persons/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne non trouvée");
        }
        model.addAttribute("person", person);
        return "editPersonForm";
    }

    @PostMapping("/persons/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, @ModelAttribute Person person) {
        Person existingPerson = personService.getPersonById(id);
        if (existingPerson == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne non trouvée");
        }

        existingPerson.setName(person.getName());
        existingPerson.setAge(person.getAge());
        personService.savePerson(existingPerson);
        return "redirect:/persons";
    }

    @PostMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/persons";
    }
}

