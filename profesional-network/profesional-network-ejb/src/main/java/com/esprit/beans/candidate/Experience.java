package com.esprit.beans.candidate;


import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity()
@Table(name="Experience")
public class Experience implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Experience_ID")
	private int experienceId;
	@Column(name="designation")
	private String designation;
	@Column(name="type")
	private String type;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	@ManyToMany( cascade=CascadeType.ALL,mappedBy="experiences")
	private Set<Candidate> candidates = new HashSet<Candidate>();
	
	public int getId() {
		return experienceId;
	}
	public void setId(int id) {
		this.experienceId = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Candidate> getCandidates() {
		return candidates;
	}
	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	


	
	
	
	
	
	
	
	
	
}
