package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "JOBOFFER")
public class JobOffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JO_ID")
	int JOid;
	@Column(name = "JO_TITLE")
	String JOtitle;
	@Column(name = "JO_AREA")
	String JOarea;
	@Column(name = "JO_DATE")
	Date JOdate;
	@Column(name = "JO_DESCRIPTION")
	String JOdescription;
	@Column(name = "JO_EXPERIENCE")
	int JOexperience;

	@ManyToOne
	HumanResources humanresources;
	@ManyToOne
	ProjectLeader projectleader;
	
	@OneToMany(mappedBy="jobOffer")
	private Set<Quiz> quizs;

	public JobOffer() {
		super();
	}

	public JobOffer(String jOtitle, String jOarea, Date jOdate, String jOdescription, int jOexperience,
			HumanResources humanresources, ProjectLeader projectleader) {
		super();
		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdate = jOdate;
		JOdescription = jOdescription;
		JOexperience = jOexperience;
		this.humanresources = humanresources;
		this.projectleader = projectleader;
	}

	public JobOffer(String jOtitle, String jOarea, Date jOdate, String jOdescription, int jOexperience) {
		super();

		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdate = jOdate;
		JOdescription = jOdescription;
		JOexperience = jOexperience;
	}

	public JobOffer(int jOid, String jOtitle, String jOarea, String jOdescription) {
		super();
		JOid = jOid;
		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdescription = jOdescription;
	}

	public JobOffer(int jOid, String jOtitle, String jOarea, Date jOdate, String jOdescription, int jOexperience) {
		super();
		JOid = jOid;
		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdate = jOdate;
		JOdescription = jOdescription;
		JOexperience = jOexperience;
	}

	public int getJOexperience() {
		return JOexperience;
	}

	public void setJOexperience(int jOexperience) {
		JOexperience = jOexperience;
	}

	public HumanResources getHumanresources() {
		return humanresources;
	}

	public void setHumanresources(HumanResources humanresources) {
		this.humanresources = humanresources;
	}

	public ProjectLeader getProjectleader() {
		return projectleader;
	}

	public void setProjectleader(ProjectLeader projectleader) {
		this.projectleader = projectleader;
	}

	public int getJOid() {
		return JOid;
	}

	public void setJOid(int jOid) {
		JOid = jOid;
	}

	public String getJOtitle() {
		return JOtitle;
	}

	public void setJOtitle(String jOtitle) {
		JOtitle = jOtitle;
	}

	public String getJOarea() {
		return JOarea;
	}

	public void setJOarea(String jOarea) {
		JOarea = jOarea;
	}

	public Date getJOdate() {
		return JOdate;
	}

	public void setJOdate(Date jOdate) {
		JOdate = jOdate;
	}

	public String getJOdescription() {
		return JOdescription;
	}

	public void setJOdescription(String jOdescription) {
		JOdescription = jOdescription;
	}

}
