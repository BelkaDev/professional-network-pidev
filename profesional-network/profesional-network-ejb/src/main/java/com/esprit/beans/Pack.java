package com.esprit.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "prix")
	private double prix;
	@Column(name="description")
	private String description;
	@Column(name="titre")
	private String titre;
	@Column(name="type")
	private TypePack type;
}
