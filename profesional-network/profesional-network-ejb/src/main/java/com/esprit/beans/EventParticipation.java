package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@Column(name="EP_RIdactionCode")
	String RidactionCode;
	
	@ManyToOne
	EnterpriseEvent enterpriseEvent;
	
	@ManyToOne
	Candidate candidate;

	@ManyToOne
	User user;
	
	
	public EventParticipation() {
		super();
	}

	
	
	public EventParticipation(int ePid, EnterpriseEvent enterpriseEvent) {
		super();
		EPid = ePid;
		this.enterpriseEvent = enterpriseEvent;
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
	
	

	public EventParticipation(int ePid, String ridactionCode, EnterpriseEvent enterpriseEvent, Candidate candidate,
			User user) {
		super();
		EPid = ePid;
		RidactionCode = ridactionCode;
		this.enterpriseEvent = enterpriseEvent;
		this.candidate = candidate;
		this.user = user;
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



	public String getRidactionCode() {
		return RidactionCode;
	}



	public void setRidactionCode(String ridactionCode) {
		RidactionCode = ridactionCode;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
		result = prime * result + ((enterpriseEvent == null) ? 0 : enterpriseEvent.hashCode());
		return result;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	
	
	
	
}
