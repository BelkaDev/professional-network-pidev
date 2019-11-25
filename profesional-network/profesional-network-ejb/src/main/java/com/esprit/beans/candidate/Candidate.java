package com.esprit.beans.candidate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.Max;

import com.esprit.beans.Interests;
import com.esprit.beans.Quiz;
import com.esprit.beans.User;
import com.esprit.enums.Tags;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Candidate")
public class Candidate extends User implements Serializable {

	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "biography")
	private String biography;
	@Column(name = "candidate_rating")
	@Max(5)
	private double rating;
	
	@Column(name="cv")
	private String cv;

	@Column(name="interests")
    private String interests;	
	

	
	
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}

	
	@JsonIgnoreProperties({"candidates"})
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Experience",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Experience_ID"))
	private Set<Experience> experiences = new HashSet<>();
	
	@JsonIgnoreProperties({"candidates"})
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Certification",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Certification_ID"))
	private Set<Certification> certifications;
	
	@JsonIgnoreProperties({"candidates"})
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Activity",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Skill_ID"))
	private Set<Activity> activities;
	
	@JsonIgnoreProperties({"candidates"})
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Candidate_Skill",joinColumns=@JoinColumn(name="Candidate_ID"),
	inverseJoinColumns=@JoinColumn(name="Skill_ID"))
	private Set<Skill> skills;
	
	@JsonIgnoreProperties({"candidate"})
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Candidate_ID")
	private Set<Contact> contacts;
	
	@JsonIgnoreProperties({"candidate"})
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="Candidate_ID")
	private Set<Views> views;
	
	@JsonIgnoreProperties({"candidate"})
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Candidate")
	private Set<Subscription> subscriptions;
	
	@JsonIgnoreProperties({"candidate"})
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Candidate",fetch = FetchType.EAGER)
	private Set<JobApplication> jobApplications;
	

	
	@JsonIgnoreProperties({"candidate"})
	@OneToMany(mappedBy="candidate")
	private Set<Quiz> quizs;

	

	
	
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
	
	
	
	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public Set<Views> getViews() {
		return views;
	}
	public void setViews(Set<Views> views) {
		this.views = views;
	}
	
	
	public Set<JobApplication> getJobApplications() {
		return jobApplications;
	}
	public void setJobApplications(Set<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}
	public Set<Quiz> getQuizs() {
		return quizs;
	}
	public void setQuizs(Set<Quiz> quizs) {
		this.quizs = quizs;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	
	
	

	

}
