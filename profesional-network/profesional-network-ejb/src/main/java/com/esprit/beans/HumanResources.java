package com.esprit.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HUMANRESOURCES")
public class HumanResources implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="HR_ID") 
	int HRid;
	@Column(name="HR_NAME") 
	String HRname;
	@Column(name="HR_PASSWORD") 
	String HRpassword;
	@Column(name="HR_EMAIL") 
	String HRemail;
	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "humanresources")
	//private Set<JobOffer> joboffer;
	
	
	
	public HumanResources(String hRname, String hRpassword, String hRemail) {
		super();
		HRname = hRname;
		HRpassword = hRpassword;
		HRemail = hRemail;
	}
	
	public HumanResources() {
		super();
	}

	public int getHRid() {
		return HRid;
	}
	public void setHRid(int hRid) {
		HRid = hRid;
	}
	public String getHRname() {
		return HRname;
	}
	public void setHRname(String hRname) {
		HRname = hRname;
	}
	public String getHRpassword() {
		return HRpassword;
	}
	public void setHRpassword(String hRpassword) {
		HRpassword = hRpassword;
	}
	public String getHRemail() {
		return HRemail;
	}
	public void setHRemail(String hRemail) {
		HRemail = hRemail;
	}
	
	
	
}
