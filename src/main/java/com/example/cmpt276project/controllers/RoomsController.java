package com.example.cmpt276project.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

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

  @PostMapping("/rooms/add")
  public String addRoom(@RequestParam Map<String,String> newroom, HttpServletResponse response){
    System.out.println("ADD room");
    String newTitle= newroom.get("name");
    String newAddress=newroom.get("address");
    String newCity=newroom.get("city");
    Double newPrice=Double.parseDouble(newroom.get("price"));
    String newDescription=newroom.get("description");
    String newStartingDate=newroom.get("startingDate");
    String newEndingDate=newroom.get("endingDate");

    roomRepo.save(new Room(newTitle,newAddress,newCity,newPrice,newDescription,newStartingDate,newEndingDate));
    response.setStatus(201);

    return "rooms/addedRoom";
  }


}
