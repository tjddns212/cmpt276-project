package com.example.cmpt276project.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private int poster; // uid of the poster
    private String nick; // nickname of the poster
    private String topic;
    @Column(length = 1000)
    private String content;
    private String time;

    public Post() {}

    public Post(int poster, String nick, String topic, String time, String content) {
        this.poster = poster;
        this.nick = nick;
        this.topic = topic;
        this.time = time;
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
