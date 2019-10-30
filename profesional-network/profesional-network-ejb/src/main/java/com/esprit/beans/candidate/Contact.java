package com.esprit.beans.candidate;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="Contact")
public class Contact implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Contact_ID")
	private int contactId;
	@Column(name="last_viewed")
	private Date lastViewed;
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public Date getLastViewed() {
		return lastViewed;
	}
	public void setLastViewed(Date lastViewed) {
		this.lastViewed = lastViewed;
	}
	
	
}
