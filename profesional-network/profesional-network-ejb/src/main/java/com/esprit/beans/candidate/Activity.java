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
@Entity()
@Table(name="Activity")
public class Activity implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="designation")
	private String designation;
	@Column(name="date")
	private Date date;
	@ManyToOne
	Candidate candidate;
}
