package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistique implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="datedebut")
	private Date datedebut;
	@Column(name="datefin")
	private Date datefin;
	@Column(name="type")
	private String type;
	@Column(name="valeur")
	private double valeur;
	@Column(name="etat")
	private String etat;
	
	public Statistique() {
		super();
	}

	public Statistique(int id, Date datedebut, Date datefin, String type,double valeur,String etat) {
		super();
		this.id = id;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.type = type;
		this.valeur=valeur;
		this.etat=etat;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getType() {
		return type;
	}

	public void setDomaine(String type) {
		this.type = type;
	}
	

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Statistique [id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", type=" + type
				+ "]";
	}
	
	
	
}
