package com.example.runtrack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
