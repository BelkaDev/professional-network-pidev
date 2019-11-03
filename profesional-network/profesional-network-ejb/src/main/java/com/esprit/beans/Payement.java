package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.OneToOne;

@Entity
public class Payement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="numCard")
	private String numCard;
	@Column(name="cvv")
	private int cvv;
	@Column(name="cardExpirationDate")
	private Date cardExpirationDate;
	@Column(name="validation")
	private boolean validation;
	@Column(name="canceled")
	private boolean canceled;
	
	@OneToOne(mappedBy="payment")
	private UserPack userPack; 
	
	public Payement() {
		
	}
	
	
	
	public Payement(int id, String numCard, int cvv, Date cardExpirationDate, boolean validation, boolean canceled,
			UserPack userPacks) {
		super();
		this.id = id;
		this.numCard = numCard;
		this.cvv = cvv;
		this.cardExpirationDate = cardExpirationDate;
		this.validation = validation;
		this.canceled = canceled;
		this.userPack = userPacks;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumCard() {
		return numCard;
	}
	public void setNumCard(String numCard) {
		this.numCard = numCard;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Date getCardExpirationDate() {
		return cardExpirationDate;
	}
	public void setCardExpirationDate(Date cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	public UserPack getUserPack() {
		return userPack;
	}
	public void setUserPack(UserPack userPacks) {
		this.userPack = userPacks;
	}
	
	
	
	
}
