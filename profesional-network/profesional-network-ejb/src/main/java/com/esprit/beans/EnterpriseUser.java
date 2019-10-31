package com.esprit.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.esprit.enums.Role;


@Entity
@Table(name = "ENTERPRISEUSER")
public class EnterpriseUser implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="ID") 
	int EUid;
	@Column(name="USERNAME") 
	String username;
	@Column(name="PASSWORD") 
	String password;
	@Column(name="EMAIL") 
	String email;
	@Column(name="TOKEN") 
	String Token;
	@Column(name = "ROLE")
	Role role;
	
	
	@ManyToOne
	Enterprise enterprise;
	
	
	
	
	
	public EnterpriseUser(String username, String password, String email, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		
	}







	public EnterpriseUser(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	

	
	
	

	public EnterpriseUser(int eUid, String username, String password, String email, String token, Role role,
			Enterprise enterprise) {
		super();
		EUid = eUid;
		this.username = username;
		this.password = password;
		this.email = email;
		Token = token;
		this.role = role;
		this.enterprise = enterprise;
	}







	public EnterpriseUser(int eUid, String username, String password, String email, String token,
			Enterprise enterprise) {
		super();
		EUid = eUid;
		this.username = username;
		this.password = password;
		this.email = email;
		Token = token;
		this.enterprise = enterprise;
	}

	public EnterpriseUser() {
		super();
	}
	
	public int getEUid() {
		return EUid;
	}
	public void setEUid(int eUid) {
		EUid = eUid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	
	
	
	
	
	
}
