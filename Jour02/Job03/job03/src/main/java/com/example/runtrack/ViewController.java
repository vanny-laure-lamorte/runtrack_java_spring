package com.example.runtrack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @GetMapping("/view")
    public String viewAnimal(Model model) {
        List<AnimalModel> animals = new ArrayList<>(
            List.of(
                new AnimalModel("Elephant", "Loxodonta africana", 10, "Savannah"),
                new AnimalModel("Tiger", "Panthera tigris", 7, "Rainforest"),
                new AnimalModel("Penguin", "Spheniscidae", 3, "Antarctica"),
                new AnimalModel("Kangaroo", "Macropus", 4, "Grassland")
            )
        );

        model.addAttribute("animals", animals);
        return "view";
    }
}
