package com.example.cmpt276project.controllers;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get login
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null){
            return "login.html";
        } else {
            model.addAttribute("user", user);
            return "redirect:/";
        }
    }

    // Post login
    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request, HttpSession session){
        String email = formData.get("email");
        String pwd = formData.get("password");
        List<User> userlist = userRepository.findByEmailAndPassword(email, pwd);

        if (userlist.isEmpty()){
            return "loginfail.html";
        } else{
            User user = userlist.get(0);
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "redirect:/";
        }
    }

    // Get logout
    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/checkLoginStatus")
    @ResponseBody
    public Map<String, Boolean> checkLoginStatus(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        User user = (User) session.getAttribute("session_user");
        response.put("loggedIn", user != null);
        return response;
    }
    //update profile 
    @GetMapping("/")
    public String index(Model model,HttpSession session){
        
        User user = (User) session.getAttribute("session_user");
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            // Add a default user object with a default nickname when the user is not logged in
            User defaultUser = new User();
            defaultUser.setNick("Default Nickname");
            model.addAttribute("user", defaultUser);
        }
        return "index";
    }
    @GetMapping("/postRoom")
        public String post(Model model,HttpSession session){
        
            User user = (User) session.getAttribute("session_user");
            User defaultUser = new User();
            defaultUser.setNick("Default Nickname");

    // Add either the user object or the default user object to the model
            if (user != null){
               model.addAttribute("user", user); 
            }
            else{
                model.addAttribute("user", defaultUser);
            }
             
            return "rooms/postRoom";
        }
    }
