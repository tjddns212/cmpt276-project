package com.example.cmpt276project.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExportController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/export/user")
    public void exportUsers(HttpServletResponse response) throws IOException {
        String data = userCSV(userRepo.findAll());

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=users.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.write(data);
        }
    }

    public String userCSV(List<User> users) {
        StringBuilder data = new StringBuilder();
        data.append("uid,first,last,nick,gender,email,password,room,accountType,avatar\n");

        for (User user : users) {
            data.append(user.getUid()).append(",")
                .append(user.getFirst()).append(",")
                .append(user.getLast()).append(",")
                .append(user.getNick()).append(",")
                .append(user.getGender()).append(",")
                .append(user.getEmail()).append(",")
                .append(user.getPassword()).append(",")
                .append(user.getRoom()).append(",")
                .append(user.getAccountType()).append(",")
                .append(user.getAvatar()).append("\n");
        }

        return data.toString();
    }
}