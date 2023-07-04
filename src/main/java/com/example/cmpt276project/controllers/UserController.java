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
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
    @Autowired
    public UserRepository userRepo;

    @Autowired
    public RoomRepository roomRepo;
    
    // @GetMapping("/user/add")
    // public String addUser(){
    //     String email = formData.get("email");
    //     List<User> userlist = userRepo.findByEmail(email);
    //     if (userlist.isEmpty()){

    //         return "login";
    //     }
    // }
    
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
        return "user/addedUser";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null){
            return "login";
        }
        else {
            model.addAttribute("user",user);
            return "mainpage";
        }
    }
    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request, HttpSession session){
        String email = formData.get("email");
        String pwd = formData.get("password");
        List<User> userlist = userRepo.findByEmailAndPassword(email,pwd);
        if (userlist.isEmpty()){

            return "login";
        }
        else{
            User user = userlist.get(0);
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "mainpage.html";
        }
        
    }
    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
//<<<<<<< Updated upstream
    
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
//=======

    
//>>>>>>> Stashed changes

        if (user.getRoom() != 0) {
            List<Room> rooms = roomRepo.findByUid(user.getRoom());
            Room room = rooms.get(0);
            room.setAddress(newuser.get("address"));
            roomRepo.save(room);
        }

        response.setStatus(201);
        return "user/editedProfile";
    }
}
