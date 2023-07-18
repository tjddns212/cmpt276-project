package com.example.cmpt276project.controllers;

import com.example.cmpt276project.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserRepository userRepo;

    @Autowired
    public MainController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index.html";
    }
}
