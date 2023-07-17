package com.example.cmpt276project.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RoomsController {
  @Autowired
  private RoomRepository roomRepo;
  @Autowired
  private ImageRepository imagesRepo;

  @GetMapping("/rooms/view")
  public String getAllRooms(Model model) {
    System.out.println("getting all rooms");

    List<Room> rooms = roomRepo.findAll();

    model.addAttribute("r", rooms);
    return "rooms/showAll";
  }

  @PostMapping("/rooms/add")
  public String addRoom(@RequestParam Map<String, String> newroom, @RequestParam("image") MultipartFile file,
      HttpServletResponse response, Model model) throws IOException {
    System.out.println("ADD room");
    String newTitle = newroom.get("name");
    String newAddress = newroom.get("address");
    String newCity = newroom.get("city");
    Double newPrice = Double.parseDouble(newroom.get("price"));
    String newDescription = newroom.get("description");
    String newStartingDate = newroom.get("startingDate");
    String newEndingDate = newroom.get("endingDate");
    Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    imagesRepo.save(image);
    Room newRoom = new Room(newTitle, newAddress, newCity, newPrice, newDescription, newStartingDate, newEndingDate);
    newRoom.setImage(image);
    roomRepo.save(newRoom);
    response.setStatus(201);
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getContentType());
    System.out.println(file.getBytes());
    System.out.println("BYTES:" + image.getImage());
    String base64String = Base64.getEncoder().encodeToString(image.getImage());
    System.out.println(base64String);
    model.addAttribute("room", newRoom);
    return "rooms/addedRoom";
  }

}
