package com.example.runtrack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Value("${greeting.message}")
    private String message;

    @GetMapping("/view")
    public String viewMessage(Model model) {
        model.addAttribute("message", message);
        return "view";
    }
}
