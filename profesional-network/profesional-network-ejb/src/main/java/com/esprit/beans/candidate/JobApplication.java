package com.esprit.beans.candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="JobApplication")
public class JobApplication {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="JobId")
	private int jobId;
	@Column(name="status")
	private String status;
	
	@ManyToOne
	private Candidate Candidate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public Candidate getCandidate() {
		return Candidate;
	}*/

	public void setCandidate(Candidate candidate) {
		Candidate = candidate;
	}
	
	
}
