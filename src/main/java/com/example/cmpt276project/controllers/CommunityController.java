package com.example.cmpt276project.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.Post;
import com.example.cmpt276project.models.PostRepository;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {
    
    @Autowired
    PostRepository postRepo;

    @Autowired
    ImageRepository imageRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping("Community")
    public String getPosts(Model model, HttpSession session) {
        List<Post> posts = postRepo.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);

        List<String> imageDatas = new ArrayList<String>();
        List<String> imageTypes = new ArrayList<String>();

        for (int i = 0; i < posts.size(); i ++) {
            List<Image> images = imageRepo.findByUid(userRepo.findByUid(posts.get(i).getPoster()).get(0).getAvatar());
            if (images.size() == 0) {
                images = imageRepo.findByUid(72);
            }
            Image image = images.get(0);
            byte[] imageData = image.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageData);

            imageDatas.add(base64Image);
            imageTypes.add(image.getType());
        }

        model.addAttribute("imageDatas", imageDatas);
        model.addAttribute("imageTypes", imageTypes);
        
        List<Integer> length = nums(posts.size());
        model.addAttribute("length", length);
        
        return "forum";
    }

    @PostMapping("Posting")
    public String newPost(@RequestParam Map<String, String> post, HttpSession session) {
        User poster = (User) session.getAttribute("session_user");
        
        if (poster == null) {
            return "login";
        }

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

    public List<Integer> nums(int n) {
        List<Integer> li = new ArrayList<Integer>();
        for (int i = 0; i < n; i ++) {
            li.add(i);
        }
        return li;
    }
}
