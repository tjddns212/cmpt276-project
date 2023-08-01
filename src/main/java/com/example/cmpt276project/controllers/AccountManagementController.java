package com.example.cmpt276project.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AccountManagementController {
    
    private final UserRepository userRepository;

    @Autowired
    public AccountManagementController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get account management
    @GetMapping("/account-management")
    public String getAccountManagement(Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "error/404.html";
        } else if (!user.isAdmin()) {
            return "error/404.html";
        }

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "uid"));
        model.addAttribute("users", users);
        return "account-management.html";
    }

    // Post Delete Account
    @PostMapping("/delete-account")
    public String postDeleteAccount(@RequestParam int uid, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "error/404.html";
        } else if (!user.isAdmin()) {
            return "error/404.html";
        }

        if (uid == user.getUid()) {
            redirectAttributes.addFlashAttribute("message", "Unable to delete itself account");
            return "redirect:/account-management";
        }

        userRepository.deleteById(uid);
        return "redirect:/account-management";
    }
}
