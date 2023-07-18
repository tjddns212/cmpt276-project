package com.example.cmpt276project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
    private final UserRepository userRepo;
    private final RoomRepository roomRepo;

    @Autowired
    public UserController(UserRepository userRepo, RoomRepository roomRepo) {
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
    }

    // Add user
    @PostMapping("user/adduser")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("Add User");

        String newEmail = newuser.get("email");
        List<User> userlist = userRepo.findByEmail(newEmail);
        if (!userlist.isEmpty()) {
            return "user/addeduserFailed.html";
        }

        String newFirst = newuser.get("first");
        String newLast = newuser.get("last");
        String newNick = newuser.get("nick");
        String newGender = newuser.get("gender");
        String newPassword = newuser.get("password");            
        userRepo.save(new User(newFirst, newLast, newNick, newGender, newEmail, newPassword, 0, "Student"));
        response.setStatus(201);
        return "user/addeduser.html";
    }

    // Add user landlord
    @PostMapping("user/adduserLandlord")
    public String addUserLandlord(@RequestParam Map<String, String> newuser, HttpServletResponse response){
        System.out.println("Add User Landlord");

        String newEmail = newuser.get("email");
        List<User> userlist = userRepo.findByEmail(newEmail);
        if (!userlist.isEmpty()) {
            return "user/addeduserFailed.html";
        }

        String newFirst = newuser.get("first");
        String newLast = newuser.get("last");
        String newNick = newuser.get("nick");
        String newGender = newuser.get("gender");
        String newPassword = newuser.get("password");           
        userRepo.save(new User(newFirst, newLast, newNick, newGender, newEmail, newPassword,0, "Landlord"));
        response.setStatus(201);
        return "user/addeduser.html";
    }

    @GetMapping("user/get")
    public String getUserByUid(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model) {
        System.out.println("Get User");
        List<User> users = userRepo.findByUid(Integer.parseInt(newuser.get("uid")));
        List<Room> rooms = roomRepo.findByUid(users.get(0).getRoom());
        model.addAttribute("users", users);
        model.addAttribute("rooms", rooms);
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

        if (user.getRoom() != 0) {
            List<Room> rooms = roomRepo.findByUid(user.getRoom());
            Room room = rooms.get(0);
            room.setAddress(newuser.get("address"));
            roomRepo.save(room);
        }

        response.setStatus(201);
        return "user/editedProfile";
    }

    // Get Reset Password
    @GetMapping("/reset-password")
    public String getResetPassword() {
        return "reset-password.html";
    }

    // Post Reset Password
    @PostMapping("/reset-password")
    public String postResetPassword(@RequestParam String email, Model model) {
        List<User> user = userRepo.findByEmail(email);

        // Email does not exist
        if (user.isEmpty()) {
            model.addAttribute("message", "This email does not exist");
            return "reset-password.html";
        }

        model.addAttribute("message", "Your password is: " + user.get(0).getPassword());
        return "reset-password.html";
    }
}
