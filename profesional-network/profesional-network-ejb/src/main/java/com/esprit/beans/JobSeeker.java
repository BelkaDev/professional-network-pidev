package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOBSEEKER")
public class JobSeeker implements Serializable {
    private static final long serialVersionUID = 1L;

    
    @Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="JS_ID") 
	int JSid;
	@Column(name="JS_LASTNAME") 
	String JSlastname;
	@Column(name="JS_FIRSTNAME") 
	String JSfirstname;
	@Column(name="JS_PASSWORD") 
	String JSpassword;
	@Column(name="JS_EMAIL") 
	String JSemail;
	
	
	
	public int getJSid() {
		return JSid;
	}
	public void setJSid(int jSid) {
		JSid = jSid;
	}
	public String getJSlastname() {
		return JSlastname;
	}
	public void setJSlastname(String jSlastname) {
		JSlastname = jSlastname;
	}
	public String getJSfirstname() {
		return JSfirstname;
	}
	public void setJSfirstname(String jSfirstname) {
		JSfirstname = jSfirstname;
	}
	public String getJSpassword() {
		return JSpassword;
	}
	public void setJSpassword(String jSpassword) {
		JSpassword = jSpassword;
	}
	public String getJSemail() {
		return JSemail;
	}
	public void setJSemail(String jSemail) {
		JSemail = jSemail;
	}
	
	
	
}
