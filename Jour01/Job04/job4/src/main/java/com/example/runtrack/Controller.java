package com.example.runtrack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${greeting.messageHello}")
    private String greetingMessageHello;

    @Value("${greeting.messageGoodbye}")
    private String greetingMessageGoodbye;

    @GetMapping("/hello")
    public String index() {
		return greetingMessageHello;
	}

  @GetMapping("/goodbye")
  public String goodbye() {
      return greetingMessageGoodbye;
  }
}
