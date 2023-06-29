package com.example.cmpt276project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cmpt276project.models.Room;

@Controller
public class RoomsController {
  @GetMapping(/*This has to be set later*/)
  public String getAllRooms(Model model){
    System.out.println("getting all rooms");

    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Find 2 people for a master room","Brentwood", 300.00,"It is super clean and wide","2023-10-01","2023-10-30")); 
    model.addAttribute("r", rooms);
    return "rooms/showAll";
  }
}
