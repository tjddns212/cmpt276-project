package com.example.cmpt276project.controllers;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;


@Controller
public class ImageController {
    
    @Autowired
    public ImageRepository imageRepo;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] imageData = file.getBytes();
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();

        Image image = new Image();

        image.setImage(imageData);
        image.setName(fileName);
        image.setType(fileType);

        System.out.println(fileName);
        System.out.println(fileType);
        imageRepo.save(image);

        return "Success";
    }

    @GetMapping("/getImage")
    public String getImage(@RequestParam Map<String, String> newuser, Model model) {
        List<Image> images = imageRepo.findByUid(Integer.parseInt(newuser.get("uid")));
        Image image = images.get(0);

        byte[] imageData = image.getImage();
        String base64Image = Base64.getEncoder().encodeToString(imageData);

        model.addAttribute("imageData", base64Image);
        return "displayImage";
    }
}
