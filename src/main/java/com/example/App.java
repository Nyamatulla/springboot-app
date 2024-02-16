package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class App {
   
    public static void main(String[] args) {
	System.setProperty("server.post", "7000");
        SpringApplication.run(App.class, args);
    }
}

@Controller
class DevopsBlogController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "DevOps Blog");
        model.addAttribute("content", "Welcome to the DevOps Blog!");
        return "index";
    }
}

