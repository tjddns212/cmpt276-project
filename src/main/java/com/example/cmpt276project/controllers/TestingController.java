package com.example.cmpt276project.controllers;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class TestingController {
    @GetMapping("/testing")
    public List<String> testValue(HttpServletResponse response){
        ArrayList<String> test = new ArrayList<>();
        test.add("test1");
        test.add("test2");
        test.add("test3");
        response.setStatus(200);
        return test;
    }
}
