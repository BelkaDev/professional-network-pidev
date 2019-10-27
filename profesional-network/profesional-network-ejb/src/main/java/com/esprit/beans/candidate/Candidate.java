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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.esprit.beans.Quiz;

@Entity
@Table(name = "Candidate")
public class Candidate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "biography")
	private String biography;
	@Column(name = "candidate_rating")
	private double rating;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
	private Set<Experience> experiences;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
	private Set<Certification> certifications;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
	private Set<Activity> activities;
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Skill> skills;
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Candidate> candidates;
	@OneToMany(mappedBy="candidate")
	private Set<Quiz> quizs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Set<Quiz> getQuizs() {
		return quizs;
	}
	public void setQuizs(Set<Quiz> quizs) {
		this.quizs = quizs;
	}

	
	
	
	
	

}
