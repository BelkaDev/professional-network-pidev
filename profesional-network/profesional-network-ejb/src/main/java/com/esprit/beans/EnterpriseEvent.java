package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENTERPRISEEVENT")
public class EnterpriseEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="EE_ID") 
	int EEid;
	@Column(name="EE_TITLE") 
	String EEtitle;
	@Column(name="EE_PLACE") 
	String EEplace;
	@Column(name="EE_SDATE") 
	Date EESdate;
	@Column(name="EE_EDATE") 
	Date EEEdate;
	@Column(name="EE_DESCRIPTION") 
	String EEdescription;
	@Column(name="EE_MINPART") 
	int EEminparticipants;
	@Column(name="EE_MAXPART") 
	int EEmaxparticipants;
	@Column(name="EE_PRICE") 
	float EEprice;
	@Column(name="EE_TICKETS") 
	int EEtickets;
	@Column(name="EE_VUES") 
	int EEvues;
	@Column(name="EE_state") 
	int EEstate;
	
	
	
	
	
	@ManyToOne
	Enterprise enterprise;
	
	
	
	

	
	public EnterpriseEvent() {
		super();
	}
	
	
	
	public EnterpriseEvent(int eEid, String eEtitle, String eEplace, Date eESdate, Date eEEdate, String eEdescription,
			Enterprise enterprise) {
		super();
		EEid = eEid;
		EEtitle = eEtitle;
		EEplace = eEplace;
		EESdate = eESdate;
		EEEdate = eEEdate;
		EEdescription = eEdescription;
		this.enterprise = enterprise;
	}
	
	
	
	
	
	
	public EnterpriseEvent(int eEid, String eEtitle, String eEplace, Date eESdate, Date eEEdate, String eEdescription,
			int eEminparticipants, int eEmaxparticipants, float eEprice, int eEtickets, int eEvues, int eEstate,
			Enterprise enterprise) {
		super();
		EEid = eEid;
		EEtitle = eEtitle;
		EEplace = eEplace;
		EESdate = eESdate;
		EEEdate = eEEdate;
		EEdescription = eEdescription;
		EEminparticipants = eEminparticipants;
		EEmaxparticipants = eEmaxparticipants;
		EEprice = eEprice;
		EEtickets = eEtickets;
		EEvues = eEvues;
		EEstate = eEstate;
		this.enterprise = enterprise;
	}

	
	
	


	public EnterpriseEvent(String eEtitle, String eEplace, Date eESdate, Date eEEdate, String eEdescription,
			int eEminparticipants, int eEmaxparticipants, float eEprice) {
		super();
		EEtitle = eEtitle;
		EEplace = eEplace;
		EESdate = eESdate;
		EEEdate = eEEdate;
		EEdescription = eEdescription;
		EEminparticipants = eEminparticipants;
		EEmaxparticipants = eEmaxparticipants;
		EEprice = eEprice;
	}



	public int getEEstate() {
		return EEstate;
	}



	public void setEEstate(int eEstate) {
		EEstate = eEstate;
	}



	public EnterpriseEvent(String eEtitle, String eEplace, Date eESdate, Date eEEdate, String eEdescription) {
		super();
		EEtitle = eEtitle;
		EEplace = eEplace;
		EESdate = eESdate;
		EEEdate = eEEdate;
		EEdescription = eEdescription;
	}



	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	
	public int getEEid() {
		return EEid;
	}
	public void setEEid(int eEid) {
		EEid = eEid;
	}
	public String getEEtitle() {
		return EEtitle;
	}
	public void setEEtitle(String eEtitle) {
		EEtitle = eEtitle;
	}
	public String getEEplace() {
		return EEplace;
	}
	public void setEEplace(String eEplace) {
		EEplace = eEplace;
	}
	public Date getEESdate() {
		return EESdate;
	}
	public void setEESdate(Date eESdate) {
		EESdate = eESdate;
	}
	public Date getEEEdate() {
		return EEEdate;
	}
	public void setEEEdate(Date eEEdate) {
		EEEdate = eEEdate;
	}
	public String getEEdescription() {
		return EEdescription;
	}
	public void setEEdescription(String eEdescription) {
		EEdescription = eEdescription;
	}
	public int getEEminparticipants() {
		return EEminparticipants;
	}
	public void setEEminparticipants(int eEminparticipants) {
		EEminparticipants = eEminparticipants;
	}
	public int getEEmaxparticipants() {
		return EEmaxparticipants;
	}
	public void setEEmaxparticipants(int eEmaxparticipants) {
		EEmaxparticipants = eEmaxparticipants;
	}
	public float getEEprice() {
		return EEprice;
	}
	public void setEEprice(float eEprice) {
		EEprice = eEprice;
	}
	public int getEEtickets() {
		return EEtickets;
	}
	public void setEEtickets(int eEtickets) {
		EEtickets = eEtickets;
	}
	public int getEEvues() {
		return EEvues;
	}
	public void setEEvues(int eEvues) {
		EEvues = eEvues;
	}
	
	
	
}
