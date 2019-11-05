package com.esprit.beans.candidate;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name="Contact")
public class Contact implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int ID;
	
	@Column(name="contact_id")
	private int contactId;
	@Column(name="status")
	private String status;
	@ManyToOne
	private Candidate Candidate;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Candidate getCandidate() {
		return Candidate;
	}
	public void setCandidate(Candidate candidate) {
		Candidate = candidate;
	}
	
	

	

	
	
}
