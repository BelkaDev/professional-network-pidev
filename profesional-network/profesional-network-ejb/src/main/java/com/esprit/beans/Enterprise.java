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
@Table(name = "ENTERPRISE")
public class Enterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="ENT_ID") 
	int Eid;
	@Column(name="ENT_NAME") 
	String Ename;
	@Column(name="ENT_DOMAIN") 
	String Edomain;
	@Column(name="ENT_LOCATION") 
	String Elocation;
	@Column(name="ENT_EMPLOYEESNUMBER") 
	int Employeesnumber;
	@Column(name="ENT_DESCRIPTION") 
	String Edescription;
	
	
	
	
	
	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "enterprise")
		//private Set<JobOffer> joboffer;
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "enterprise")
	//private Set<EnterpriseEvent> event;
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "enterprise")
		//private Set<EnterpriseUser> Euser;
	
	public Enterprise() {
		super();
	}
	
	
	
	
	
	public Enterprise(int eid, String ename, String edomain, String elocation, int employeesnumber,
			String edescription) {
		super();
		Eid = eid;
		Ename = ename;
		Edomain = edomain;
		Elocation = elocation;
		Employeesnumber = employeesnumber;
		Edescription = edescription;
	}





	public Enterprise(String ename, String edomain, String elocation, int employeesnumber, String edescription) {
		super();
		Ename = ename;
		Edomain = edomain;
		Elocation = elocation;
		Employeesnumber = employeesnumber;
		Edescription = edescription;
	}





	





	public String getElocation() {
		return Elocation;
	}
	public void setElocation(String elocation) {
		Elocation = elocation;
	}
	public String getEdescription() {
		return Edescription;
	}
	public void setEdescription(String edescription) {
		Edescription = edescription;
	}
	public int getEid() {
		return Eid;
	}
	
	
	public int getEmployeesnumber() {
		return Employeesnumber;
	}
	public void setEmployeesnumber(int employeesnumber) {
		Employeesnumber = employeesnumber;
	}
	public void setEid(int eid) {
		Eid = eid;
	}
	public String getEname() {
		return Ename;
	}
	public void setEname(String ename) {
		Ename = ename;
	}
	public String getEdomain() {
		return Edomain;
	}
	public void setEdomain(String edomain) {
		Edomain = edomain;
	}

	
	
	
	
	

	
	
}
