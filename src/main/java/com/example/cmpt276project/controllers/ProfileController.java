package com.example.cmpt276project.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    public UserRepository userRepo;

    // @Autowired
    // public RoomRepository roomRepo;

    @Autowired
    public ImageRepository imageRepo;

    @GetMapping("user/get")
    public String getUserByUid(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        List<User> users = userRepo.findByUid(user.getUid());
        // List<Room> rooms = roomRepo.findByUid(users.get(0).getRoom());

        model.addAttribute("users", users);
        // model.addAttribute("rooms", rooms);
        response.setStatus(201);
        return "user/Profile";
    }

    @PostMapping("user/edit")
    public String editUserByUid(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        List<User> users = userRepo.findByUid(Integer.parseInt(newuser.get("uid")));
        User user = users.get(0);
        user.setFirst(newuser.get("first"));
        user.setLast(newuser.get("last"));
        user.setNick(newuser.get("nick"));
        user.setGender(newuser.get("gender"));
        user.setEmail(newuser.get("email"));
        user.setPassword(newuser.get("password"));
        userRepo.save(user);

        // if (user.getRoom() != 0) {
        //     List<Room> rooms = roomRepo.findByUid(user.getRoom());
        //     Room room = rooms.get(0);
        //     room.setAddress(newuser.get("address"));
        //     roomRepo.save(room);
        // }

        response.setStatus(201);
        return "user/editedProfile";
    }

}
