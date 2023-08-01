package com.example.cmpt276project.controllers;
import com.example.cmpt276project.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;


@Controller
public class FaqController {

    @GetMapping("/faq")
      public String showFaq(Model model,HttpSession session){
        
        User user = (User) session.getAttribute("session_user");
        User defaultUser = new User();
        defaultUser.setNick("Default Nickname");

        if (user != null){
           model.addAttribute("user", user); 
        }
        else{
            model.addAttribute("user", defaultUser);
        }
        return "faq";  
    }
  
}

