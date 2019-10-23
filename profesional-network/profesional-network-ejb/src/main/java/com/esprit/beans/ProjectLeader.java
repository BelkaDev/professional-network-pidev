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
@Table(name = "PROJECTLEADER")
public class ProjectLeader  implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="PL_ID") 
	int PLid;
	@Column(name="PL_NAME") 
	String PLname;
	@Column(name="PL_PASSWORD") 
	String PLpassword;
	@Column(name="PL_EMAIL") 
	String PLemail;
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectleader")
	//private Set<JobOffer> joboffer;
	
	
	public ProjectLeader(String pLname, String pLpassword, String pLemail) {
		super();
		PLname = pLname;
		PLpassword = pLpassword;
		PLemail = pLemail;
	}
	
	
	public ProjectLeader() {
		super();
	}


	public int getPLid() {
		return PLid;
	}
	public void setPLid(int pLid) {
		PLid = pLid;
	}
	public String getPLname() {
		return PLname;
	}
	public void setPLname(String pLname) {
		PLname = pLname;
	}
	public String getPLpassword() {
		return PLpassword;
	}
	public void setPLpassword(String pLpassword) {
		PLpassword = pLpassword;
	}
	public String getPLemail() {
		return PLemail;
	}
	public void setPLemail(String pLemail) {
		PLemail = pLemail;
	}
	
	
}
