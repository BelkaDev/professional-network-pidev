package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Claim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="description")
	private String description;
	@Enumerated(EnumType.STRING)
	@Column(name="state")
	private State state;
	@Column(name="type")
	private String type;
	@JsonIgnore
	@ManyToOne 
	private User whoClaim; 
	@JsonIgnore	
	@ManyToOne
	private User claimsOn;
	@Column(name="date")
	private Date date;
	
	public Claim() {
		super();
	}
	public Claim(int id, String description, State state, String type, User whoClaim, User claimsOn) {
		super();
		this.id = id;
		this.description = description;
		this.state = state;
		this.type = type;
		this.whoClaim = whoClaim;
		this.claimsOn = claimsOn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getWhoClaim() {
		return whoClaim;
	}
	public void setWhoClaim(User whoClaim) {
		this.whoClaim = whoClaim;
	}
	public User getClaimsOn() {
		return claimsOn;
	}
	public void setClaimsOn(User claimsOn) {
		this.claimsOn = claimsOn;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Claim [id=" + id + ", description=" + description + ", state=" + state + ", type=" + type
				+ "]";
	}
	
	
	
	
	
	
}
