package com.example.cmpt276project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;

@Controller
public class RoomsController {
  @Autowired
  private RoomRepository roomRepo;

  @GetMapping("/rooms/view")
  public String getAllRooms(Model model){
    System.out.println("getting all rooms");

    List<Room> rooms = roomRepo.findAll();
    
    model.addAttribute("r", rooms);
    return "rooms/showAll";
  }
}
