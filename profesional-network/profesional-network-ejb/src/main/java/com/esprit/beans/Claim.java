package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	@Column(name="etat")
	private Etat etat;
	@Column(name="type")
	private String type;
	@ManyToOne
	private User whoClaim; 
	@ManyToOne
	private User claimsOn; 
	
	public Claim() {
		super();
	}
	

	public Claim(int id, String description, Etat etat, String type, User whoClaim, User claimsOn) {
		super();
		this.id = id;
		this.description = description;
		this.etat = etat;
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

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
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

	public void setClaimOn(User claimsOn) {
		this.claimsOn = claimsOn;
	}


	
	
}
