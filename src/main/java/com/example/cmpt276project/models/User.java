package com.example.cmpt276project.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	private String first;
	private String last;
	private String nick;
	private String gender;
	private String email;
	private String password;
	private int room; // it stores the room id of the user because it is hard to store a whole room in the user table
	private String accountType;
	private int avatar; // it stores the uid of user's avatar in images table

	public User() {}

	public User(String first, String last, String nick, String gender, String email, String password, int room, String accountType) {
		this.first = first;
		this.last = last;
		this.nick = nick;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.room = room;
		this.accountType = accountType;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
}
