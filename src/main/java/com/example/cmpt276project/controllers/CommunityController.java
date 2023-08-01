package com.example.cmpt276project.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.Post;
import com.example.cmpt276project.models.PostRepository;
import com.example.cmpt276project.models.User;

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
    public String newPost(@RequestParam Map<String, String> post, HttpSession session) {
        User poster = (User) session.getAttribute("session_user");
        
        Post p = new Post();

        p.setTopic(post.get("title"));
        p.setContent(post.get("content"));
        p.setNick(poster.getNick());
        p.setPoster(poster.getUid());
        p.setTime(getTime());
        
        postRepo.save(p);
        
        return "Success";
    }

    public String getTime() {
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fdt = dt.format(f);

        return fdt;
    }
}
