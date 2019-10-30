package com.esprit.beans.candidate;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.esprit.enums.Rating;

@Entity()
@Table(name = "skill")
public class Skill implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name="designation")
	private String designation;
	@Column(name="skill_rating")
	private Rating rating;
	
}
