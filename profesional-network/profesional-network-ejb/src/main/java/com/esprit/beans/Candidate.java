package com.esprit.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Candidate")
public class Candidate implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="biography")
	private String biography;
	@Column(name="candidate_rating")
	private double rating;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="candidate")
	private Set<Experience> experiences;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="candidate")
	private Set<Certification> certifications;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="candidate")
	private Set<Activity> activities;
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Skill> skills;
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Candidate> candidates;
	
	
	
	
	

}
