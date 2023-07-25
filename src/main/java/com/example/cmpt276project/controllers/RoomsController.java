package com.example.cmpt276project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cmpt276project.models.Image;
import com.example.cmpt276project.models.ImageRepository;
import com.example.cmpt276project.models.Room;
import com.example.cmpt276project.models.RoomRepository;
import com.example.cmpt276project.models.User;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RoomsController {
  @Autowired
  private RoomRepository roomRepo;
  @Autowired
  private ImageRepository imagesRepo;

  @GetMapping("/rooms/view")
  public String getAllRooms(Model model, HttpSession session) {
    System.out.println("getting all rooms");
    User user = (User) session.getAttribute("session_user");


    List<Room> rooms = roomRepo.findAll();

    model.addAttribute("r", rooms);
    User defaultUser = new User();
    defaultUser.setNick("Default Nickname");

    // Add either the user object or the default user object to the model
    model.addAttribute("user", user != null ? user : defaultUser);

    return "rooms/showAll";
  }

  @PostMapping("/rooms/add")
  public String addRoom(@RequestParam Map<String, String> newroom, @RequestParam("image") MultipartFile file,
      HttpServletResponse response, Model model, HttpSession session) throws IOException {
    System.out.println("ADD room");
    User user = (User) session.getAttribute("session_user");
    String newTitle = newroom.get("title");
    String newAddress = newroom.get("address");
    String newCity = newroom.get("city");
    Double newPrice = Double.parseDouble(newroom.get("price"));
    String newDescription = newroom.get("description");
    String newStartingDate = newroom.get("startingDate");
    String newEndingDate = newroom.get("endingDate");
    Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    imagesRepo.save(image);
    Room newRoom = new Room(newTitle, newAddress, newCity, newPrice, newDescription, newStartingDate, newEndingDate,
        user.getUid());
    newRoom.setImage(image);
    roomRepo.save(newRoom);
    response.setStatus(201);
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getContentType());
    System.out.println(file.getBytes());
    System.out.println("BYTES:" + image.getImage());
    String base64String = Base64.getEncoder().encodeToString(image.getImage());
    System.out.println(base64String);
    model.addAttribute("roomListing", newRoom);
    return "rooms/roomListing";
  }

  @GetMapping("/ownerListings/{id}")
  public String getOwnerListings(Model model, @PathVariable Integer id, HttpSession session) {
    User user = (User) session.getAttribute("session_user");
    List<Room> listings = roomRepo.findAll().stream()
        .filter(room -> room.getOwner_id() == user.getUid()).collect(Collectors.toList());

    model.addAttribute("listings", listings);
    model.addAttribute("user", user);
    return "rooms/ownerListings";
  }

}
