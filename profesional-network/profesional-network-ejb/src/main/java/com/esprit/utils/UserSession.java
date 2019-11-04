package com.esprit.utils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.beans.Address;
import com.esprit.beans.User;
import com.esprit.enums.Gender;
import com.esprit.enums.Role;

public final class UserSession {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	private static UserSession instance;

	private int id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private boolean recieveMailNotifs;

	private Gender gender;

	private Date birthDate;

	public boolean enable;

	private String confirm;

	private Address address;


	private String username;

	private String token;

	private Role role;
	
	private String interests;

	public UserSession(String username, String password, int id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public UserSession(int id, String email, String firstName, String lastName, String password,
			boolean recieveMailNotifs, Gender gender, Date date, boolean enable, String confirm, Address address,
			  String username, String token, Role role,String interests) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.recieveMailNotifs = recieveMailNotifs;
		this.gender = gender;
		this.birthDate = date;
		this.enable = enable;
		this.confirm = confirm;
		this.address = address;
		this.username = username;
		this.token = token;
		this.role = role;
		this.interests=interests;
	}

	public UserSession() {
	}

	public static UserSession getInstance(User user) {
		if (instance == null) {
			instance = new UserSession(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), 
					user.getRecieveMailNotifs(), user.getGender(), user.getBirthDate(), user.isEnable(), user.getConfirm(), user.getAddress(),
					 user.getUsername(), user.getToken(), user.getRole(),user.getInterests());
		}
		return instance;
	}
	

	public static UserSession getInstance() {
		if (instance == null) {
			instance = new UserSession();
		}
		return instance;
	}

	
	public void cleanUserSession() {

		instance = null;

	}


	public static void setInstance(UserSession instance) {
		UserSession.instance = instance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRecieveMailNotifs() {
		return recieveMailNotifs;
	}

	public void setRecieveMailNotifs(boolean recieveMailNotifs) {
		this.recieveMailNotifs = recieveMailNotifs;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}
	

}
