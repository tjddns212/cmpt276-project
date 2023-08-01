package com.example.cmpt276project.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public ImageRepository imageRepo;

    @GetMapping("user/get")
    public String getUserByUid(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        
        User user = (User) session.getAttribute("session_user");
        System.out.println("\n\n\n");
        System.out.println(user.getUid());
        System.out.println("\n\n\n");
        List<User> users = userRepo.findByUid(user.getUid());
        try {
            List<Image> images = imageRepo.findByUid(user.getAvatar());

            Image image = images.get(0);
            byte[] imageData = image.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageData);

            model.addAttribute("imageData", base64Image);
            model.addAttribute("imageType", image.getType());
        } catch (Exception e) {}

        model.addAttribute("user", users.get(0));
        response.setStatus(201);
        return "user/Profile";
    }

    @PostMapping("user/edit")
    public String editUserByUid(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        
        List<User> users = userRepo.findByUid(Integer.parseInt(newuser.get("uid")));
        User user = users.get(0);
        List<User> all = userRepo.findByEmail(newuser.get("email"));

        if (all.size() > 0 && !user.getEmail().equals(newuser.get("email"))) {
            return "user/emailUsed";
        }
        user.setFirst(newuser.get("first"));
        user.setLast(newuser.get("last"));
        user.setNick(newuser.get("nick"));
        user.setGender(newuser.get("gender"));
        user.setEmail(newuser.get("email"));
        user.setPassword(newuser.get("password"));
        userRepo.save(user);

        response.setStatus(201);
        return "user/editedProfile";
    }

    @PostMapping("user/changeAvatar")
    public String changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session, Model model) throws IOException {
        
        User user = (User) session.getAttribute("session_user");

        try {
            byte[] imageData = file.getBytes();
            String fileName = file.getOriginalFilename();
            String fileType = file.getContentType();

            Image image = new Image();

            image.setImage(imageData);
            image.setName(fileName);
            image.setType(fileType);

            imageRepo.save(image);
            user.setAvatar((int) image.getUid());
            userRepo.save(user);

            String base64Image = Base64.getEncoder().encodeToString(imageData);
            model.addAttribute("imageData", base64Image);
            model.addAttribute("imageType", image.getType());

        } catch (Exception e) {}

        model.addAttribute("user", user);
        return "user/Profile";
    }
}
