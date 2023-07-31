package com.example.cmpt276project.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.cmpt276project.models.Image;
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
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);
        List<User> possibleRoommates = userRepo.findAll().stream()
                .filter(possibleRoommate -> possibleRoommate.getUid() != user.getUid()
                        && possibleRoommate.getAccountType().equals("Student"))
                .collect(Collectors.toList());
        model.addAttribute("roommates", possibleRoommates);
        return "roommates/RoommatesSearch";
    }

    @GetMapping("/ProfileInfo/{uid}")
    public String deleteListing(@PathVariable Integer uid, Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        model.addAttribute("user", user);
        User profileUser = userRepo.findById(uid).get();
        model.addAttribute("profileUser", profileUser);
        System.out.println("-----------------------------------=============");
        Optional<Image> profileImage = imagesRepo.findById(Long.valueOf(profileUser.getAvatar()));
        Image image = null;
        if (profileImage.isPresent()) {
            image = profileImage.get();
        }
        System.out.println(profileImage);
        model.addAttribute("profileImage", image);

        return "roommates/ProfileInfo";
    }
}
