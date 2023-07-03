package com.example.cmpt276project.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    
    @Autowired
    public UserRepository userRepo;
    
    @PostMapping("user/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("Add User");
        String newFirst = newuser.get("first");
        String newLast = newuser.get("last");
        String newNick = newuser.get("nick");
        String newGender = newuser.get("gender");
        String newEmail = newuser.get("email");
        String newPassword = newuser.get("password");            
        userRepo.save(new User(newFirst, newLast, newNick, newGender, newEmail, newPassword,0));
        response.setStatus(201);
        return "user/addedUser.html";
    }
    


}
