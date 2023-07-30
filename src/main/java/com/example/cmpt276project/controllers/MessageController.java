package com.example.cmpt276project.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.MessageRepository;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {
    
    @Autowired
    public UserRepository userRepo;

    @Autowired
    public MessageRepository messageRepo;

    @GetMapping("DM")
    public String getMessages(@RequestParam Map<String, String> targetUser, Model model, HttpSession session) {
        return "DirectMessages";
    }

    @PutMapping("message/send")
    public String sendMessage(@RequestParam Map<String, String> newMsg, HttpSession session) {
        return "DirectMessages";
    }
}
