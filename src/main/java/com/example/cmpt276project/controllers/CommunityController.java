package com.example.cmpt276project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cmpt276project.models.Post;
import com.example.cmpt276project.models.PostRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {
    
    @Autowired
    PostRepository postRepo;

    @GetMapping("Community")
    public String getPosts(Model model, HttpSession session) {
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "forum";
    }

    @PostMapping("Posting")
    public String newPost(HttpSession session) {
        return "posting";
    }
}
