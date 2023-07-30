package com.example.cmpt276project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class RoommatesController {
    @Autowired
    private ImageRepository imagesRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/findRoomates")
    public String showPossibleRoommates(Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        model.addAttribute("user", user);

        return "roommates/RoommatesSearch";
    }
}
