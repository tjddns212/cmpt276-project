package com.example.cmpt276project.controllers;
import com.example.cmpt276project.models.Question;
import com.example.cmpt276project.models.QuestionRepository;
import com.example.cmpt276project.models.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class FaqController {
  @Autowired
  private QuestionRepository questionRepo;

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

        List<Question> posts = questionRepo.findAll();
        model.addAttribute("Question", posts);
        return "faq";  
    }

    @GetMapping("/post-question")
      public String postFaq(Model model,HttpSession session){
        User user = (User) session.getAttribute("session_user");
        User defaultUser = new User();
        defaultUser.setNick("Default Nickname");

        if (user != null){
           model.addAttribute("user", user); 
        }
        else{
            model.addAttribute("user", defaultUser);
        }
        return "postFaq";
      }
  

    @PostMapping("/postQ")
      public String addPost(@RequestParam Map<String,String> newpost, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        
        User user = (User) session.getAttribute("session_user");
        User defaultUser = new User();
        defaultUser.setNick("Default Nickname");

        if (user != null){
           model.addAttribute("user", user); 
        }
        else{
            model.addAttribute("user", defaultUser);
        }
        String newTitle= newpost.get("title");
        String newDescription=newpost.get("body");
        String newAnswer=newpost.get("answer");
        String newNick = user.getNick();
        LocalDate newDataPosted = LocalDate.now();

        Question newPost= new Question(newTitle, newDescription, newAnswer, newNick, newDataPosted);

        model.addAttribute("Question", newPost);
        questionRepo.save(newPost);
        List<Question> posts = questionRepo.findAll();
        model.addAttribute("Question", posts);
        return "faq";
      } 
      @PostMapping("/submitAnswer")
      public String submitAnswer(@RequestParam("answer") String answer,Model model, @RequestParam("qid") int qid,HttpSession session) {
          Question question = questionRepo.findByQid(qid);
          if(question != null){
              question.setAnswer(answer);
              questionRepo.save(question);
          }
          User user = (User) session.getAttribute("session_user");
          User defaultUser = new User();
          defaultUser.setNick("Default Nickname");
  
          if (user != null){
             model.addAttribute("user", user); 
          }
          else{
              model.addAttribute("user", defaultUser);
          }
          List<Question> posts = questionRepo.findAll();
        model.addAttribute("Question", posts);
          return "faq"; // Redirects to /faq after the operation
      }
}

