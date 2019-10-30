package com.esprit.beans.candidate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.esprit.beans.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Candidate")
public class Candidate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int candidateId;
	@Column(name = "biography")
	private String biography;
	@Column(name = "candidate_rating")
	private double rating;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Experience",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Experience_ID"))
	private Set<Experience> experiences = new HashSet<>();
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Certification",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Certification_ID"))
	private Set<Certification> certifications;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Activity",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Skill_ID"))
	private Set<Activity> activities;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Skill",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Skill_ID"))
	private Set<Skill> skills;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Candidate_ID")
	private Set<Contact> contacts;
	
	
	@OneToMany(mappedBy="candidate")
	private Set<Quiz> quizs;

	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Set<Experience> getExperiences() {
		return experiences;
	}
	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}
	public Set<Certification> getCertifications() {
		return certifications;
	}
	public void setCertifications(Set<Certification> certifications) {
		this.certifications = certifications;
	}
	public Set<Activity> getActivities() {
		return activities;
	}
	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
	public Set<Skill> getSkills() {
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
	
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	public Set<Quiz> getQuizs() {
		return quizs;
	}
	public void setQuizs(Set<Quiz> quizs) {
		this.quizs = quizs;
	}

	
	
	
	
	

	
	
	
	
	

}
