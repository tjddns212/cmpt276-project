package com.example.cmpt276project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import java.util.List;

@Controller
public class AccountManagementController {
    
    private final UserRepository userRepository;

    @Autowired
    public AccountManagementController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/account-management")
    public String getAccountManagement(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        
        return "account-management.html";
    }
}