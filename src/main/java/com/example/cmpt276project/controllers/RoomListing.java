package com.example.cmpt276project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;
import com.example.cmpt276project.models.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class RoomListing {
    @Autowired
    private RoomRepository roomsRepo;

    @GetMapping("/roomListing/{id}")
    public String getRoomListing(Model model, @PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        Room roomListing = roomsRepo.findById(id).get();
        model.addAttribute("roomListing", roomListing);
        model.addAttribute("user", user);
        
        return "rooms/roomListing";
    }
}
