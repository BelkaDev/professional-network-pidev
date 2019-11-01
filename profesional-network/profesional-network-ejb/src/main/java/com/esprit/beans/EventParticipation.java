package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.esprit.beans.candidate.Candidate;

@Entity
@Table(name="EventParticipation")
public class EventParticipation  implements Serializable{
	private static final long serialVersionUID = 1L;

	
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="EP_ID") 
	int EPid;
	
	
	@ManyToOne
	EnterpriseEvent enterpriseEvent;
	
	@ManyToOne
	Candidate candidate;

	
	
	public EventParticipation() {
		super();
	}

	
	
	public EventParticipation(EnterpriseEvent enterpriseEvent, Candidate candidate) {
		super();
		this.enterpriseEvent = enterpriseEvent;
		this.candidate = candidate;
	}



	public EventParticipation(int ePid, EnterpriseEvent enterpriseEvent, Candidate candidate) {
		super();
		EPid = ePid;
		this.enterpriseEvent = enterpriseEvent;
		this.candidate = candidate;
	}

	public int getEPid() {
		return EPid;
	}

	public void setEPid(int ePid) {
		EPid = ePid;
	}

	public EnterpriseEvent getEnterpriseEvent() {
		return enterpriseEvent;
	}

	public void setEnterpriseEvent(EnterpriseEvent enterpriseEvent) {
		this.enterpriseEvent = enterpriseEvent;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
	
	
}
