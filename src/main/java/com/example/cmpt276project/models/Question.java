package com.example.cmpt276project.models;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="questions")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int qid;
  private String title;
  private String description;
  private String answer;
  private String nick;
  private LocalDate dataPosted;

  public Question() {
  }

  public Question(String title, String description, String answer, String nick, LocalDate dataPosted) {
    this.title = title;
    this.description=description;
    this.answer = answer;
    this.nick = nick;
    this.dataPosted = dataPosted;
  }

  public int getQid() {
    return qid;
  }

  public void setQid(int qid) {
    this.qid = qid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public LocalDate getDataPosted() {
    return dataPosted;
  }

  public void setDataPosted(LocalDate dataPosted) {
    this.dataPosted = dataPosted;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  
}

