package com.example.cmpt276project.models;

import jakarta.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String title;
    private String address;
    private String city;
    private Double price;
    private String description;
    private String startingDate;
    private String endingDate;
    public Room() {
    }
    public Room(String title, String address,String city, Double price, String description, String startingDate,
        String endingDate) {
      this.title = title;
      this.address = address;
      this.price = price;
      this.city=city;
      this.description = description;
      this.startingDate = startingDate;
      this.endingDate = endingDate;
    }
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }
    public Double getPrice() {
      return price;
    }
    public void setPrice(Double price) {
      this.price = price;
    }
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    public String getStartingDate() {
      return startingDate;
    }
    public void setStartingDate(String startingDate) {
      this.startingDate = startingDate;
    }
    public String getEndingDate() {
      return endingDate;
    }
    public void setEndingDate(String endingDate) {
      this.endingDate = endingDate;
    }
    public int getUid() {
      return uid;
    }
    public void setUid(int uid) {
      this.uid = uid;
    }
    public String getCity() {
      return city;
    }
    public void setCity(String city) {
      this.city = city;
    }
    

}
