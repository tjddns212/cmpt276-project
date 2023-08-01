package com.example.cmpt276project.controllers;

import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.cmpt276project.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostManagementController {

    private final RoomRepository roomRepository;

    @Autowired
    public PostManagementController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Get posts
    @GetMapping("/post-management")
    public String getPosts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "redirect:/error/404.html";
        } else if (!user.isAdmin()) {
            return "redirect:/error/404.html";
        }

        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "post-management.html";
    }

    // Delete a post
    @PostMapping("/delete-post")
    public String deletePost(@RequestParam int uid, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "redirect:/error/404.html";
        } else if (!user.isAdmin()) {
            return "redirect:/error/404.html";
        }

        roomRepository.deleteById(uid);
        return "redirect:/post-management";
    }
}
