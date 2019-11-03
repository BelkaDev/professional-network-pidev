package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.esprit.beans.candidate.Candidate;
import com.esprit.enums.Tags;

@Entity
@Table(name = "Interests")
public class Interests implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Tags tag;
	private JobOffer jobOffer;
	private Candidate candidate;


	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="id") 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public Tags getTag() {
		return tag;
	}

	public void setTag(Tags tag) {
		this.tag = tag;
	}

 	@ManyToOne
 	@JoinColumn(name = "jobOffer" , referencedColumnName = "JO_ID")
	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

 	@ManyToOne
 	@JoinColumn(name = "candidate" , referencedColumnName = "ID")
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
}
