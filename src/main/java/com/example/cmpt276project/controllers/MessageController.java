package com.example.cmpt276project.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.Message;
import com.example.cmpt276project.models.MessageRepository;
import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {
    
    @Autowired
    public UserRepository userRepo;

    @Autowired
    public MessageRepository messageRepo;

    @GetMapping("DM")
    public String getMessages(@RequestParam Map<String, String> targetUser, Model model, HttpSession session, HttpServletRequest request) {

        List<User> users;
        if (targetUser.get("uid") != null) {
            users = userRepo.findByUid(Integer.parseInt(targetUser.get("uid")));
        }
        else if (targetUser.get("nick") != null) {
            users = userRepo.findByNick(targetUser.get("nick"));
        }
        else if (targetUser.get("email") != null) {
            users = userRepo.findByEmail(targetUser.get("email"));
        }
        else if (targetUser.get("first name") != null) {
            users = userRepo.findByFirst(targetUser.get("first name"));
        }
        else {
            return "redirect:/";
        }
        if (users.size() == 0) {
            return "redirect:/";
        }
        User receiver = users.get(0);
        User sender = (User) session.getAttribute("session_user");

        List<Message> messages = messageRepo.findBySenderAndReceiver(sender.getUid(), receiver.getUid());
        messages.addAll(messageRepo.findBySenderAndReceiver(receiver.getUid(), sender.getUid()));
        
        messages = sortMessage(messages);

        request.getSession().setAttribute("dmUser", receiver);
        model.addAttribute("messages", messages);

        return "DirectMessages";
    }

    @PostMapping("DM")
    public String sendMessage(@RequestParam Map<String, String> newMsg, HttpSession session, Model model) {

        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fdt = dt.format(f);

        User sender = (User) session.getAttribute("session_user");
        User receiver = (User) session.getAttribute("dmUser");

        Message msg = new Message();
        msg.setContent(newMsg.get("content"));
        msg.setSender(sender.getUid());
        msg.setReceiver(receiver.getUid());
        msg.setTime(fdt);

        messageRepo.save(msg);

        List<Message> messages = messageRepo.findBySenderAndReceiver(sender.getUid(), receiver.getUid());
        messages.addAll(messageRepo.findBySenderAndReceiver(receiver.getUid(), sender.getUid()));
        messages = sortMessage(messages);

        model.addAttribute("messages", messages);

        return "DirectMessages";
    }

    private List<Message> sortMessage(List<Message> messages) {
        for (int i = 0; i < messages.size(); i ++) {
            for (int j = 0; j < i; j ++) {
                if (messages.get(i).getMid() < messages.get(j).getMid()) {
                    Collections.swap(messages, i, j);
                }
            }
        }
        return messages;
    }
}
