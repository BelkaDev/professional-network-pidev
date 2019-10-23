package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENTREPRISEADMIN")
public class EnterpriseAdmin  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="EADMIN_ID") 
	int EAid;
	@Column(name="EADMIN_NAME") 
	String EAname;
	@Column(name="EADMIN_PASSWORD") 
	String EApassword;
	@Column(name="EADMIN_EMAIL") 
	String EAemail;
	
	
	
	
	
	
	public EnterpriseAdmin(String eAname, String eApassword, String eAemail) {
		super();
		EAname = eAname;
		EApassword = eApassword;
		EAemail = eAemail;
	}


	public EnterpriseAdmin() {
		super();
	}
	
	
	public int getEAid() {
		return EAid;
	}
	public void setEAid(int eAid) {
		EAid = eAid;
	}
	public String getEAname() {
		return EAname;
	}
	public void setEAname(String eAname) {
		EAname = eAname;
	}
	public String getEApassword() {
		return EApassword;
	}
	public void setEApassword(String eApassword) {
		EApassword = eApassword;
	}
	public String getEAemail() {
		return EAemail;
	}
	public void setEAemail(String eAemail) {
		EAemail = eAemail;
	}
	
	
	
}
