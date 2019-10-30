package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserPack implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=true,updatable=false)
	private User user;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="idPack",referencedColumnName="id",insertable=true,updatable=false)
	private Pack pack;
	@Column(name="daysleft")
	private int daysleft;
	@Column(name="isValid")
	private boolean isValid;
	@Column(name="startDate")
	private Date startDate;
	@Column(name="endDate")
	private Date endDate;
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public UserPack() {
		
	}
	
	public UserPack( User user, Pack pack, int daysleft,boolean isValid) {
		super();
		this.isValid=isValid;
		this.user = user;
		this.pack = pack;
		this.daysleft = daysleft;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Pack getPack() {
		return pack;
	}
	public void setPack(Pack pack) {
		this.pack = pack;
	}
	public int getDaysleft() {
		return daysleft;
	}
	public void setDaysleft(int daysleft) {
		this.daysleft = daysleft;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "UserPack [id=" + id + ", user=" + user + ", pack=" + pack + ", daysleft=" + daysleft +" , isValid="+isValid+ "]";
	}
	
	
	
}
