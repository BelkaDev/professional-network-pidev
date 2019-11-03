package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 7a8bdf103001a35b5e8e1dc952ff1723121a1243
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.esprit.enums.Tags;

@Entity
@Table(name = "JOBOFFER")
public class JobOffer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="JO_ID") 
	int JOid;
	@Column(name="JO_TITLE") 
	String JOtitle;
	@Column(name="JO_AREA") 
	String JOarea;
	@Column(name="JO_DATE") 
	Date JOdate;
	@Column(name="JO_DESCRIPTION") 
	String JOdescription;
	@Column(name="JO_EXPERIENCE") 
	int JOexperience;
	@Column(name="JO_ISVALID")
	int isValid;
	@Column(name="JO_VUES")
	int VuesNumber;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jobOffer", fetch = FetchType.EAGER)
	private Set<Interests> Interests;
	
	@OneToMany(mappedBy="jobOffer")
	private Set<Quiz> quizs;
	
	@ManyToOne
	Enterprise enterprise;

	public JobOffer() {
		super();
	}

	
	public JobOffer(String jOtitle, String jOarea, String jOdescription, int jOexperience) {
		super();
		
		JOtitle = jOtitle;
		JOarea = jOarea;
		
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



	
	
	public JobOffer(int jOid, String jOtitle, String jOarea, Date jOdate, String jOdescription, int jOexperience,
			int isValid, Enterprise enterprise) {
		super();
		JOid = jOid;
		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdate = jOdate;
		JOdescription = jOdescription;
		JOexperience = jOexperience;
		this.isValid = isValid;
		this.enterprise = enterprise;
	}







	public JobOffer(String jOtitle, String jOarea, Date jOdate, String jOdescription, int jOexperience, int isValid,
			Enterprise enterprise) {
		super();
		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdate = jOdate;
		JOdescription = jOdescription;
		JOexperience = jOexperience;
		this.isValid = isValid;
		this.enterprise = enterprise;
	}







	public JobOffer(int jOid, String jOtitle, String jOarea, Date jOdate, String jOdescription, int jOexperience,
			int isValid, int vuesNumber, Enterprise enterprise) {
		super();
		JOid = jOid;
		JOtitle = jOtitle;
		JOarea = jOarea;
		JOdate = jOdate;
		JOdescription = jOdescription;
		JOexperience = jOexperience;
		this.isValid = isValid;
		VuesNumber = vuesNumber;
		this.enterprise = enterprise;
	}







	public int getJOexperience() {
		return JOexperience;
	}
	public void setJOexperience(int jOexperience) {
		JOexperience = jOexperience;
	}


	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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


	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public int getVuesNumber() {
		return VuesNumber;
	}

	public void setVuesNumber(int vuesNumber) {
		VuesNumber = vuesNumber;
	}

	

	public Set<Interests> getInterests() {
		return Interests;
	}


	public void setInterests(Set<Interests> interests) {
		Interests = interests;






	public Set<Quiz> getQuizs() {
		return quizs;
	}







	public void setQuizs(Set<Quiz> quizs) {
		this.quizs = quizs;
	}
	
	
	
	
}
