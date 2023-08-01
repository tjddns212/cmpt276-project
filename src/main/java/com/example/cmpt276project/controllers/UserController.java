package com.example.cmpt276project.controllers;

import java.util.List;
import java.util.Map;

import com.example.cmpt276project.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;
import com.example.cmpt276project.models.RoomRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    
    private final UserRepository userRepo;
    private final RoomRepository roomRepo;
    private final EmailService emailService;

    private final String MAIL_SUBJECT = "Password of RoomLink Account";
    private final String MAIL_MESSAGE_TEMPLATE = """
                    Dear %s,
                    
                        Thanks for using our forget password function, here is your password: %s.
                        
                    Best regards,
                    Room Link Team
                    """;

    @Autowired
    public UserController(UserRepository userRepo, RoomRepository roomRepo, EmailService emailService) {
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
        this.emailService = emailService;
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

    // Get Reset Password
    @GetMapping("/forgetPassword")
    public String getResetPassword() {
        return "forgetPassword.html";
    }

    // Post Reset Password
    @PostMapping("/forgetPassword")
    public String postResetPassword(@RequestParam String email, Model model) {
        List<User> users = userRepo.findByEmail(email);

        // Email does not exist
        if (users.isEmpty()) {
            model.addAttribute("message", "This email does not exist");
            return "forgetPassword.html";
        }

        User user = users.get(0);
        try {
            String toEmailAddress = user.getEmail();
            String mailMessage = String.format(MAIL_MESSAGE_TEMPLATE, user.getNick(), user.getPassword());
            emailService.sendEmail(toEmailAddress, MAIL_SUBJECT, mailMessage);
            model.addAttribute("message", "The password has been sent to " + user.getEmail());
//            model.addAttribute("message", "Your password is " + user.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "forgetPassword.html";
    }
}
