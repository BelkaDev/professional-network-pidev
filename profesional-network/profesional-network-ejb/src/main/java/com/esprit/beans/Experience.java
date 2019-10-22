package com.esprit.beans;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="Experience")
public class Experience implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="designation")
	private String designation;
	@Column(name="type")
	private String type;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	
	@ManyToOne
	Candidate candidate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getStart_date() {
		return startDate;
	}
	public void setStart_date(Date start_date) {
		this.startDate = start_date;
	}
	public Date getEnd_date() {
		return endDate;
	}
	public void setEnd_date(Date end_date) {
		this.endDate = end_date;
	}
	
	
	
	
}
