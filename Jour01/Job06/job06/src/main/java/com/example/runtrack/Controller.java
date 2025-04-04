package com.example.runtrack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${greeting.message}")
    private String greetingMessage;

    @GetMapping("/DevTool")
    public String index() {
		return greetingMessage;
	  }

}
